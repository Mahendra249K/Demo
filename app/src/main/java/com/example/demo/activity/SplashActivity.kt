package com.example.demo.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.demo.R
import com.example.demo.databinding.ActivitySplashBinding
import com.example.demo.utility.Utility


class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun createBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemBars()
        val bitmap1 = Utility.decodeSampledBitmapFromResource(
            resources, R.mipmap.ic_launcher, 512, 512
        )
        binding.ivLogo.setImageBitmap(bitmap1)
        gotoHome()
    }


    private fun gotoHome() {
        Handler(Looper.getMainLooper()).postDelayed({
            val bundle = intent.extras
            val intent = Intent(this@SplashActivity, NoticeActivity::class.java)
            bundle?.let { intent.putExtras(it) }
            startActivity(intent)
            finish()
        }, 3000)
    }


    private fun hideSystemBars() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = ViewCompat.getWindowInsetsController(
            window.decorView
        ) ?: return
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}