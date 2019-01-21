package com.umesh.MVPSample.APIHelper

import android.annotation.SuppressLint
import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.umesh.MVPSample.BuildConfig
import com.umesh.MVPSample.KotlinMVPSampleApplication
import com.umesh.MVPSample.baseframework.models.APIError
import com.umesh.MVPSample.baseframework.models.GenericModel
import com.umesh.MVPSample.response_models.LoginRequest
import com.umesh.MVPSample.response_models.LoginResponse
import com.umesh.MVPSample.utils.Utility
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException

open class RestApisImpl(internal var mEventBus: EventBus, internal var mContext: Context) {

    private lateinit var retrofit: Retrofit
    private lateinit var mRestApis: RestApis

    fun RestApisObj() {
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client((mContext.getApplicationContext() as KotlinMVPSampleApplication).getClient())
                .build()

        mRestApis = retrofit.create(RestApis::class.java)
    }


    @SuppressLint("CheckResult")
    fun doLogin(reqString: LoginRequest) {


/*        {
                "code": 200,
                "url": "Users/commonlogin.json",
                "message": "Login successfully.",
                "data": {
                    "Users": {
                            "id": 001,
                            "firstname": "test",
                            "lastname": "user",
                            "username": "testUser",
                            "email": "testUser@gmail.com",
                            "user_profile": "",
                            "modified": "2018-12-26T08:57:27"
                            }
                        }
        }
*/

        /*
         * For this Architecture API response are same as above for all API
         * Like code, message, and data(JsonObject) are fixed for all API
         * Users data is should be anything by api(Ex. In login api it is "Users")
         * Users should be any things JsonArray or JsonObject
         */


        val getusers = mRestApis.doLogin(Utility.getRequest(KotlinMVPSampleApplication.getGsonWithExpose().toJson(reqString)))
        getusers.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn { throwable ->
                    try {
                        val message = (throwable as HttpException).response().errorBody()!!.string()
                        val userBaseResponse: GenericModel<LoginResponse> = KotlinMVPSampleApplication.getGsonWithExpose().fromJson(message, GenericModel<LoginResponse>()::class.java)
                        return@onErrorReturn userBaseResponse
                    } catch (e: SocketTimeoutException) {
                        val userBaseResponse: GenericModel<LoginResponse> = KotlinMVPSampleApplication.getGsonWithExpose().fromJson("Server not responding", GenericModel<LoginResponse>()::class.java)
                        userBaseResponse.code = 100
                        return@onErrorReturn userBaseResponse

                    } catch (e: Exception) {
                        val userBaseResponse: GenericModel<LoginResponse> = KotlinMVPSampleApplication.getGsonWithExpose().fromJson("Server not responding", GenericModel<LoginResponse>()::class.java)
                        userBaseResponse.code = 100
                        return@onErrorReturn userBaseResponse
                    }
                }
                .subscribe { responsData ->
                    if (responsData != null) {
                        if (responsData.code == 200) {
                            val loginResponse: LoginResponse? = responsData.getResponseModel(LoginResponse::class.java)
                            loginResponse?.setMessage(responsData.getMessage())
                            mEventBus.post(loginResponse)
                        } else {
                            mEventBus.post(APIError(105, responsData.message))
                        }
                    }
                }
    }

}