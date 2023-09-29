package com.example.demo.utility

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.demo.R
import com.example.demo.listner.onNetworkConnectionReciver

class NetworkConnectionHandler {

    companion object {
        fun handleConnection(
            activity: Activity,
            connected: Boolean,
            parent: ConstraintLayout,
            imageView: ImageView,
            tvErrorText: TextView,
            listner: onNetworkConnectionReciver
        ) {

            if (connected) {
                listner.onNetworkConnected()
            } else {
                parent.visibility = View.VISIBLE
                Glide.with(activity).load(android.R.drawable.ic_dialog_alert)
                    .placeholder(android.R.color.darker_gray).into(imageView)
                tvErrorText.text =
                    activity.getString(R.string.no_check_your_connection_or_try_again)
            }
        }
    }
}