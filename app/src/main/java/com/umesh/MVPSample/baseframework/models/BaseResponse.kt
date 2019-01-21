package com.umesh.MVPSample.baseframework.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("message")
    @Expose
    private lateinit var message: String

    fun getMessage(): String {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }
}