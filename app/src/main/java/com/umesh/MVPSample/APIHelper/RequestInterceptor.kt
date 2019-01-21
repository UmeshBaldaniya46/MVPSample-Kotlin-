package com.umesh.MVPSample.APIHelper

import android.content.Context
import com.umesh.MVPSample.utils.Constants
import com.umesh.MVPSample.utils.Prefs
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor(context: Context) : Interceptor {


    private var credentials = ""

    init {
        val prefs: Prefs = Prefs(context)
        if (prefs.userData.id != null && prefs.userData.email != null && prefs.userData.apiPlainKey != null) {
            this.credentials = Credentials.basic(prefs.userData.email!!, prefs.userData.apiPlainKey!!)
        }
    }

    @Throws(IOException::class)
    override fun intercept(aChain: Interceptor.Chain): Response {

        val builder = aChain.request().newBuilder()
        builder.addHeader(Constants.ApiHeaders.AUTHORIZATION, credentials)
        val request = builder.build()
        return aChain.proceed(request)
    }
}