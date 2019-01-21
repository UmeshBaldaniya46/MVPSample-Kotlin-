package com.umesh.MVPSample.baseframework.mvp

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationUtils
import com.umesh.MVPSample.utils.Utility

class BaseFragment : Fragment(), BaseView {

    override fun getViewActivity(): Activity {
        return (this).activity!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showProgress() {
        Utility.showProgressDialog(getViewActivity(), "")
    }

    override fun hideProgress() {
        Utility.hideProgressDialog()
    }

    override fun showToast(message: String) {

        Utility.showSnackBar(getViewActivity().findViewById<View>(android.R.id.content), message,
                Snackbar.LENGTH_LONG, false, getViewActivity())
        hideProgress()
    }

    override fun showError(message: String) {
        if (!TextUtils.isEmpty(message)) {
            Utility.showSnackBar(getViewActivity().findViewById<View>(android.R.id.content), message,
                    Snackbar.LENGTH_LONG, true, getViewActivity())
            hideProgress()
        }
    }

    override fun showSnackBar(message: String) {
        Utility.showSnackBar(getViewActivity().findViewById<View>(android.R.id.content), message,
                Snackbar.LENGTH_LONG, false, getViewActivity())
    }

    override fun onNetworkStateChange(isConnect: Boolean) {

    }

    fun setViewVisibility(viewIn: View, viewOut: View) {

        val animation_fade_in = AnimationUtils.loadAnimation(getViewActivity(), android.R.anim.fade_in)
        val animation_fade_out = AnimationUtils.loadAnimation(getViewActivity(), android.R.anim.fade_out)

        viewIn.visibility = View.VISIBLE
        viewIn.startAnimation(animation_fade_in)
        viewOut.startAnimation(animation_fade_out)
        viewOut.visibility = View.GONE

    }

}