package com.example.demo.listner

interface OnResponseListner {
    fun onResposnseFail()
    fun onResposnseSuccess(sucess: Int?)
}