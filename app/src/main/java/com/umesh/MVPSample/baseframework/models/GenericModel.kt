package com.umesh.MVPSample.baseframework.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.umesh.MVPSample.KotlinMVPSampleApplication

open  class GenericModel<Umesh> {

    internal var ResponseJson: Umesh? = null

    @Expose
    @SerializedName("code")
    internal var code: Int = 0

    @Expose
    @SerializedName("url")
    internal var url: String? = null

    @Expose
    @SerializedName("message")
    internal lateinit var message: String

    @Expose
    @SerializedName("response")
    internal lateinit var response: Any

    fun getResponseModel(aModel: Class<Umesh>): Umesh? {
        setResponseJson(aModel)
        return ResponseJson
    }

    fun getResponse(): Any {
        return response
    }

    fun setResponse(response: String) {
        this.response = response
    }

    fun getMessage(): String {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun setResponseJson(aModel: Class<Umesh>) {
        ResponseJson = prepareModel(response, aModel)
    }

    protected fun <T> prepareModel(aString: Any, aClass: Class<T>): T {
        return KotlinMVPSampleApplication.getGsonWithExpose().fromJson(KotlinMVPSampleApplication.getGsonWithExpose().toJson(aString), aClass)
    }


    fun getStatus(): Int {
        return code
    }

    fun setStatus(status: Int) {
        this.code = status
    }


}
