package com.example.demo.utility

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager

class DialogUtils {

    companion object {
        fun makeDialog(
            activity: Activity,
            dialogLayout: Int,
            isCanceledOnTouchOutside: Boolean,
            isCancelable: Boolean
        ): Dialog {
            val dialog = Dialog(activity)
            dialog.setContentView(dialogLayout)
            dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
            dialog.setCancelable(isCancelable)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.attributes?.windowAnimations =
                android.R.style.Animation_Dialog
            return dialog
        }

        fun showDialog(activity: Activity, dialog: Dialog) {
            if (!activity.isFinishing && !dialog.isShowing) {
                dialog.show()
            }
        }

        fun dismissDialog(activity: Activity, dialog: Dialog) {
            if (!activity.isFinishing && dialog.isShowing) {
                dialog.dismiss()
            }
        }

    }
}