package com.umesh.MVPSample.baseframework.mvp

import android.app.Activity

interface BaseView {

    abstract fun showProgress()

    abstract fun hideProgress()

    abstract fun showToast(message: String)

    abstract fun showError(message: String)

    abstract fun showSnackBar(message: String)

     fun getViewActivity(): Activity

    abstract fun onNetworkStateChange(isConnect: Boolean)
}