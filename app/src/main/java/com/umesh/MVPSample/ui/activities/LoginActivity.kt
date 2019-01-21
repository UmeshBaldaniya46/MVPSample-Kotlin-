package com.umesh.MVPSample.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import com.umesh.MVPSample.KotlinMVPSampleApplication
import com.umesh.MVPSample.R
import com.umesh.MVPSample.baseframework.mvp.BaseActivity
import com.umesh.MVPSample.mvp.model.LoginActivityModel
import com.umesh.MVPSample.mvp.presenters.LoginPresenter
import com.umesh.MVPSample.mvp.presenters.LoginPresenterImpl
import com.umesh.MVPSample.response_models.LoginResponse
import com.umesh.MVPSample.utils.Prefs
import com.umesh.MVPSample.utils.Utility
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), View.OnClickListener, LoginPresenter.LoginView {


    //Implementer to create business logic for same
    private var presenter: LoginPresenterImpl? = null
    //Model for store data of Request Result
    private var model: LoginActivityModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenterImpl(this)
        presenter?.mRestApisImpl?.RestApisObj()
        model = LoginActivityModel(this)

        initclickListner();

        val prefs: Prefs = Prefs(getViewActivity())
        if (prefs.isRemember) {
            edtUserNmeLoginActivity.setText(prefs.EmailRemember)
            edtPasswordLoginActivity.setText(prefs.PassRemember)
            cbRememberMeLoginActivity.isChecked = true
        }
    }

    private fun initclickListner() {

        txtSigninLoginActivity.setOnClickListener(this)
        pbSigninLoginActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.txtSigninLoginActivity -> {

                Utility.setSingInAnimation(ivSigninLoginActivity, txtSigninLoginActivity, pbSigninLoginActivity)
                doRetrieveModel().setRequestLogin(edtUserNmeLoginActivity.text.toString(), edtPasswordLoginActivity.text.toString())
                presenter?.doLogin()

            }

            R.id.pbSigninLoginActivity -> {
                ivSigninLoginActivity.visibility = View.VISIBLE
                pbSigninLoginActivity.visibility = View.GONE

                ivSigninLoginActivity.rotation = 180f
                removeAnimation(txtSigninLoginActivity)
            }
        }
    }

    private fun removeAnimation(v: View) {

        var width = v.width;
        width -= ivSigninLoginActivity!!.width;
        val anim = TranslateAnimation(width.toFloat(), 0f, 0f, 0f)
        anim.duration = 600
        anim.fillAfter = true
        ivSigninLoginActivity?.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                ivSigninLoginActivity.rotation = 0f
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }


    override fun onResume() {
        super.onResume()
        /**
         * Register event bus
         */
        presenter?.registerBus()
    }

    override fun onPause() {
        super.onPause()
        /**
         * Unregister event bus
         */
        presenter?.unRegisterBus()
    }

    override fun showError(message: String) {
        pbSigninLoginActivity.callOnClick()
        Utility.showSnackBar(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG, true, this)
    }

    override fun showSnackBar(message: String) {
        Utility.showSnackBar(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT, true, this)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun showProgress() {
        Utility.showProgressDialog(this, "")
    }

    override fun hideProgress() {
        Utility.hideProgressDialog()
    }

    override fun getViewActivity(): Activity {
        return this
    }

    override fun onNetworkStateChange(isConnect: Boolean) {

    }


    /**
     * Fire when successful LOGIN
     */
    override fun onLoginSuccessful(loginResponse: LoginResponse) {

        val data = loginResponse.data
        if (data?.apiKey != null) {

            val prefs: Prefs = Prefs(getViewActivity())
            prefs.userData = data
            (getViewActivity().applicationContext as KotlinMVPSampleApplication).resetClient()

            if (cbRememberMeLoginActivity.isChecked) {
                prefs.EmailRemember = edtUserNmeLoginActivity.text.toString().trim();
                prefs.PassRemember = edtPasswordLoginActivity.text.toString().trim();
            } else {
                prefs.EmailRemember = "";
                prefs.PassRemember = "";
            }
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()

        } else {
            showError(loginResponse.getMessage())
        }
    }

    override fun onRegistrationTap(intent: Intent) {
        startActivity(intent)
    }

    override fun onForgotPasswordTap(intent: Intent) {
        startActivity(intent)
    }

    override fun doRetrieveModel(): LoginActivityModel {
        return model!!
    }

}

