package com.example.demo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ParentModel {
    @SerializedName("success")
    @Expose
    var success: Int? = null

    @SerializedName("currPage")
    @Expose
    var currPage: Int? = null

    @SerializedName("totalPages")
    @Expose
    var totalPages: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = ""

    @SerializedName("fav_id")
    @Expose
    var favId: String? = ""

    @SerializedName("filename")
    @Expose
    var filename: String? = ""

    @SerializedName("Balance")
    @Expose
    var balance: String? = ""

    @SerializedName("Payable")
    @Expose
    var payable: String? = ""

    @SerializedName("currency")
    @Expose
    var currency: String? = ""

    @SerializedName("payment_message")
    @Expose
    var paymentMessage: String? = ""

    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("reason")
    @Expose
    var reason: String? = null

    @SerializedName("user_token")
    @Expose
    var user_token: String? = ""

    @SerializedName("type")
    @Expose
    var type: String? = ""

    @SerializedName("emails")
    @Expose
    var emails: ArrayList<EmailModel>? = null

}