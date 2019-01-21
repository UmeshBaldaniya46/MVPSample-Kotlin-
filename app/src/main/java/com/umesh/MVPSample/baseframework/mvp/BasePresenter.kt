package com.umesh.MVPSample.baseframework.mvp

interface BasePresenter {

    /**
     * Method that should signal the appropriate view to show the appropriate error with the
     * provided message.
     */
    abstract fun onError(message: String)

    abstract fun registerBus()

    abstract fun unRegisterBus()
}