package com.umesh.MVPSample.APIHelper

import com.umesh.MVPSample.baseframework.models.GenericModel
import com.umesh.MVPSample.response_models.LoginResponse
import com.umesh.MVPSample.utils.Constants
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface RestApis {

    @POST(Constants.ApiMethods.GET_LOGIN)
    abstract fun doLogin(@Body requestBody: RequestBody): Flowable<GenericModel<LoginResponse>>

}