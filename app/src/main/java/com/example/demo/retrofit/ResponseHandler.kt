package com.example.demo.retrofit

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.demo.listner.OnResponseListner
import com.example.demo.model.ParentModel
import com.example.demo.utility.Utility

class ResponseHandler {

    companion object {

        fun onHandleResponse(
            activity: Activity,
            model: ParentModel?,
            isShowAction: Boolean,
            listner: OnResponseListner,
            tvErrorText: TextView? = null,
            parent: ConstraintLayout? = null,
            pageNo: Int? = null
        ) {
            when (model!!.success) {
                0 -> {
                    if (pageNo != null) {
                        if (pageNo == 1) {
                            if (parent != null && tvErrorText != null) {
                                parent.visibility = View.VISIBLE
                                tvErrorText.text = model.message
                            }
                        }
                    } else {
                        if (parent != null && tvErrorText != null) {
                            parent.visibility = View.VISIBLE
                            tvErrorText.text = model.message
                        }
                    }
                    listner.onResposnseFail()
                }

                1 -> {
                    if (isShowAction) {
                        Utility.showToast(activity, model.message)
                    }
                    if (parent != null) {
                        parent.visibility = View.GONE
                    }
                    listner.onResposnseSuccess(model.success)
                }

                else -> {

                }
            }
        }


    }
}