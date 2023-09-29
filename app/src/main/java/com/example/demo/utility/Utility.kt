package com.example.demo.utility

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.demo.MyApplication
import com.google.android.material.snackbar.Snackbar


class Utility {

    companion object {
        @JvmStatic
        fun decodeSampledBitmapFromResource(
            res: Resources?, resId: Int,
            reqWidth: Int, reqHeight: Int
        ): Bitmap {

            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeResource(res, resId, options)

            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

            options.inJustDecodeBounds = false
            Log.i("decodeSampled Method", "Done.....")
            return BitmapFactory.decodeResource(res, resId, options)
        }

        fun calculateInSampleSize(
            options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
        ): Int {
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1
            if (height > reqHeight || width > reqWidth) {
                val halfHeight = height / 2
                val halfWidth = width / 2

                while (halfHeight / inSampleSize >= reqHeight
                    && halfWidth / inSampleSize >= reqWidth
                ) {
                    inSampleSize *= 2
                }
            }
            Log.i("Calculate Size Method", "Done........")
            return inSampleSize
        }


        fun printLog(key: String?, value: String?) {
            Log.v(key, value!!)
        }


        fun showToast(activity: Activity, msg: String?) {
            if (!activity.isFinishing) {
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
            }
        }

        fun showSnackBar(activity: Activity, msg: String?, view: View) {
            if (!activity.isFinishing) {
                Snackbar.make(
                    view,
                    msg!!,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }


        fun isConnected(): Boolean {
            val cm = MyApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }

    }

}