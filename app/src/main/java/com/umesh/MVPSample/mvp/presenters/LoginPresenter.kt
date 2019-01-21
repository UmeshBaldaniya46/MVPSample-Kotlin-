package com.umesh.MVPSample.mvp.presenters

import android.content.Intent
import com.umesh.MVPSample.baseframework.mvp.BaseView
import com.umesh.MVPSample.mvp.model.LoginActivityModel
import com.umesh.MVPSample.response_models.LoginResponse

interface LoginPresenter {

    //this interface is used to setup UI after any api or db call.
    interface LoginView : BaseView {

        fun onLoginSuccessful(loginResponse: LoginResponse)

        fun onRegistrationTap(intent: Intent)

        fun onForgotPasswordTap(intent: Intent)

        fun doRetrieveModel(): LoginActivityModel
    }

    abstract fun doLogin()

    abstract fun gotoRegistration()

    abstract fun gotoForgotPassord(email: String)
}