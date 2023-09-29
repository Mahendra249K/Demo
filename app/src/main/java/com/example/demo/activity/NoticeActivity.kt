package com.example.demo.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.adapter.NoticeAdapter
import com.example.demo.databinding.ActivityNoticeBinding
import com.example.demo.listner.OnResponseListner
import com.example.demo.listner.onNetworkConnectionReciver
import com.example.demo.model.EmailModel
import com.example.demo.retrofit.ResponseHandler
import com.example.demo.utility.DialogUtils
import com.example.demo.utility.NetConnectivityReceiver
import com.example.demo.utility.NetworkConnectionHandler


class NoticeActivity : BaseActivity<ActivityNoticeBinding>(),
    NoticeAdapter.onNoticeClickListner {

    var arrayList: ArrayList<EmailModel>? = ArrayList()
    var mAdapter: NoticeAdapter? = null
    var mPDFPathNotice: String? = ""
    var mFileDateNotice: String? = ""

    override fun createBinding(): ActivityNoticeBinding =
        ActivityNoticeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setLinearProgressIndicator(binding.layPaginationProgress.linearProgressIndicator)
        setObservers()
        setToolbar()
        setNotice()


        NetworkConnectionHandler.handleConnection(
            this, NetConnectivityReceiver.isConnected,
            binding.layNoData.parent, binding.layNoData.imageView,
            binding.layNoData.tvErrorText, object :
                onNetworkConnectionReciver {
                override fun onNetworkConnected() {
                    callNoticeAPI()
                }
            })

    }


    private fun setToolbar() {

//        binding.toolbar.tvToolbarTitle.text = getString(R.string.app_name)
//
//        val toggle_menu =
//            Utility.decodeSampledBitmapFromResource(resources, R.drawable.ic_back, 256, 256)
//        binding.toolbar.ivBack.setImageBitmap(toggle_menu)
//        binding.toolbar.ivBack.setOnClickListener {
//            onBackPressed()
//        }
    }


    private fun callNoticeAPI() {
        isLoadingAPI = false
        authViewModel.loadEmail(
            pageNo.toString(),
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }

    private fun setObservers() {
        authViewModel.errorMessage.observe(this) {
            if (!it.isNullOrEmpty()) {
                showToast(it)
                DialogUtils.dismissDialog(this, dialog!!)
            }
        }

        authViewModel.jsonObjectResponseLoadEmail.observe(this) {
            if (it != null) {
                val model = parseViewModel.parseModel(it)
                DialogUtils.dismissDialog(this, dialog!!)

                ResponseHandler.onHandleResponse(this, model, true, object :
                    OnResponseListner {
                    override fun onResposnseFail() {
                        isLoadingAPI = false
                        if (pageNo == 1) {
                            binding.rvNotice.visibility = View.GONE
                            showToast(model.message)
                        }
                    }

                    override fun onResposnseSuccess(sucess: Int?) {
                        binding.rvNotice.visibility = View.VISIBLE

                        if (pageNo == 1) {
                            mAdapter?.addDataList(true, model.emails!!)
                        } else {
                            mAdapter?.addDataList(false, model.emails!!)
                        }
                        checkPaginatingStatus(model.currPage!!, model.totalPages!!)
                    }
                }, binding.layNoData.tvErrorText, binding.layNoData.parent, pageNo)
            }
        }

        authViewModel.isLoading.observe(this) {
            if (it) {
                if (pageNo == 1) {
                    DialogUtils.showDialog(this, dialog!!)
                } else {
                    showLinearProgressIndicator()
                }
            } else {
                DialogUtils.dismissDialog(this, dialog!!)
                hideLinearProgressIndicator()
            }
        }
    }


    private fun setNotice() {
        val mLayoutManager = LinearLayoutManager(this)
        binding.rvNotice.layoutManager = mLayoutManager

        mAdapter = NoticeAdapter(this, arrayList!!, this)
        binding.rvNotice.adapter = mAdapter

        binding.rvNotice.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = mLayoutManager.childCount
                val totalItemCount = mLayoutManager.itemCount
                val firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()

                if (isLoadingAPI && visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    callNoticeAPI()
                }
            }
        })
    }


    override fun onNoticeDownloadClick(model: EmailModel) {
        mPDFPathNotice = model.msgAttachment
        mFileDateNotice = model.msgEntryDate!!

    }

}