package com.example.demo

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this

    }

    companion object {
        private var mInstance: MyApplication? = null

        fun getInstance(): MyApplication {
            return mInstance!!
        }
    }

    @Override
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}