package com.example.demo.activity


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.demo.R
import com.example.demo.utility.DialogUtils
import com.example.demo.viewModel.AuthViewModel
import com.example.demo.viewModel.ParseViewModel


abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: V
    private var progressBar: ProgressBar? = null

    //pagination
    var pageNo = 1
    var isLoadingAPI = false
    private var linearProgressIndicator: ProgressBar? = null


    lateinit var authViewModel: AuthViewModel
    lateinit var parseViewModel: ParseViewModel
    var dialog: Dialog? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = createBinding()
        this.binding = binding
        setContentView(binding.root)
        init()
    }

    abstract fun createBinding(): V


    private fun init() {
        dialog = DialogUtils.makeDialog(this, R.layout.dialog_loading, false, false)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        parseViewModel = ViewModelProvider(this)[ParseViewModel::class.java]
    }

    fun checkPaginatingStatus(current_page: Int, last_page: Int) {
        if (current_page < last_page) {
            pageNo += 1
            isLoadingAPI = true
        } else {
            isLoadingAPI = false
        }
    }

    fun setLinearProgressIndicator(bar: ProgressBar) {
        linearProgressIndicator = bar
    }

    fun showLinearProgressIndicator() {
        linearProgressIndicator?.visibility = View.VISIBLE
    }

    fun hideLinearProgressIndicator() {
        linearProgressIndicator?.visibility = View.INVISIBLE
    }


    fun showToast(msg: String?) {
        if (!this.isFinishing) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun setProgressBar(): Dialog {
        val loadialog = Dialog(this)
        loadialog.setContentView(R.layout.dialog_loading)
        loadialog.setCanceledOnTouchOutside(false)
        loadialog.setCancelable(false)
        loadialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        loadialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadialog.window?.attributes?.windowAnimations =
            android.R.style.Animation_Dialog
        return loadialog
    }

    fun hideKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyBoard(etSearch: EditText) {
        etSearch.requestFocus()
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(etSearch, InputMethodManager.SHOW_IMPLICIT)
    }


}