package com.umesh.MVPSample.mvp.model

import android.content.Context
import com.umesh.MVPSample.baseframework.mvp.BaseViewModel
import com.umesh.MVPSample.mvp.domains.LoginActivityDomain
import com.umesh.MVPSample.response_models.LoginRequest

// initialization of any array for related list is done here
class LoginActivityModel(context: Context) : BaseViewModel(context) {

    var mainActivityDomain: LoginActivityDomain = LoginActivityDomain()
    lateinit var  requestLogin: LoginRequest
        private set

    fun setRequestLogin(username: String, password: String) {
        val request = LoginRequest()
        request.setEmail(username)
        request.setPassword(password)
        this.requestLogin = request
    }
}
