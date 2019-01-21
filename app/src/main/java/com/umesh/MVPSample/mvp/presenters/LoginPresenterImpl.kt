package com.umesh.MVPSample.mvp.presenters

import android.text.TextUtils
import android.util.Log
import com.umesh.MVPSample.APIHelper.RestApisImpl
import com.umesh.MVPSample.R
import com.umesh.MVPSample.baseframework.models.APIError
import com.umesh.MVPSample.response_models.LoginResponse
import com.umesh.MVPSample.utils.ConnectivityReceiver
import com.umesh.MVPSample.utils.Utility
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class LoginPresenterImpl(view1: LoginPresenter.LoginView) : LoginPresenter {

    private var view: LoginPresenter.LoginView = view1
    private var mEventBus: EventBus = EventBus.getDefault()
    var mRestApisImpl: RestApisImpl = RestApisImpl(mEventBus, view.getViewActivity())

    fun onError(message: String) {
        view.showError(message)
    }

    fun registerBus() {
        mEventBus.register(this)
    }

    fun unRegisterBus() {
        mEventBus.unregister(this)
    }


    @Subscribe(sticky = true)
    fun onHttpError(throwable: Throwable) {
        view.showError(throwable.cause!!.getLocalizedMessage())
        view.hideProgress()
        mEventBus.removeStickyEvent(throwable)
    }


    @Subscribe(sticky = true)
    fun onItemsResponse(event: APIError) {
        view.showError(event.getHttpErrorMessage())
        view.hideProgress()
        mEventBus.removeStickyEvent(event)
    }


    @Subscribe(sticky = true)
    fun onEvent(event: LoginResponse) {
        Log.e("event", event.toString())
        view.doRetrieveModel().mainActivityDomain.setUserResponse(event)
        view.hideProgress()
        if (event.data != null) {
            view.onLoginSuccessful(event)
        } else {
            view.showError(event.getMessage())
        }
        mEventBus.removeStickyEvent(event)

    }

    override fun doLogin() {

        if (ConnectivityReceiver.isNetworkConnected(view.getViewActivity())) {

            val request = view.doRetrieveModel().requestLogin

            Log.e("doLogin ", "request " + request.getEmail())
            Log.e("doLogin ", "request " + request.getPassword())

            Utility.hideKeyboard(view.getViewActivity())

            if (request.getEmail() == null || TextUtils.isEmpty(request.getEmail().toString().trim())) {
                view.showError(view.getViewActivity().getString(R.string.label_enter_email_id))
            } else if (TextUtils.isEmpty(request.getPassword().toString().trim())) {
                view.showError(view.getViewActivity().getString(R.string.label_enter_password))
            } else {
                //view.showProgress()
                mRestApisImpl.doLogin(request)
            }
        } else {
            view.showError(view.getViewActivity().getString(R.string.no_internet_message))
        }
    }

    override fun gotoRegistration() {
        // view.onRegistrationTap(Intent(view.getViewActivity(), SignUpActivity::class.java))
    }

    override fun gotoForgotPassord(email: String) {
        //view.onForgotPasswordTap(Intent(view.getViewActivity(), ForgotPasswordActivity::class.java).putExtra("email", email))
    }
}