package com.umesh.MVPSample.response_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequest {


    @SerializedName("email")
    @Expose
    private var email: String? = null
    @SerializedName("password")
    @Expose
    private var password: String? = null
    @SerializedName("role_id")
    @Expose
    private val role_id = "2"

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }
}