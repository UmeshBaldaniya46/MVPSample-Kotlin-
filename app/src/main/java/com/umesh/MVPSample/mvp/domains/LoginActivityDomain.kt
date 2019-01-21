package com.umesh.MVPSample.mvp.domains

import com.umesh.MVPSample.response_models.LoginResponse

class LoginActivityDomain {


    private var userResponse: LoginResponse? = null

    fun getUserResponse(): LoginResponse? {
        return userResponse
    }

    fun setUserResponse(userResponse: LoginResponse) {
        this.userResponse = userResponse
    }

}