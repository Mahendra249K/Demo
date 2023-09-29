package com.example.demo.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class EmailModel {

    @SerializedName("msg_id")
    @Expose
    var msgId: String? = null

    @SerializedName("msg_subject")
    @Expose
    var msgSubject: String? = null

    @SerializedName("msg_text_sample")
    @Expose
    var msgTextSample: String? = null

    @SerializedName("msg_text")
    @Expose
    var msgText: String? = null

    @SerializedName("msg_attachment")
    @Expose
    var msgAttachment: String? = null

    @SerializedName("msg_entry_date")
    @Expose
    var msgEntryDate: String? = null


}