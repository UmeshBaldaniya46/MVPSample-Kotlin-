package com.umesh.MVPSample.response_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.umesh.MVPSample.baseframework.models.BaseResponse

class LoginResponse : BaseResponse() {

    @SerializedName("Users")
    @Expose
    var data: UserInfo? = null

}