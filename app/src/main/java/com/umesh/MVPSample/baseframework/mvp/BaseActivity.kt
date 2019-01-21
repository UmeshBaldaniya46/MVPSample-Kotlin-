package com.umesh.MVPSample.baseframework.mvp

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import com.umesh.MVPSample.R
import com.umesh.MVPSample.utils.Utility

abstract class BaseActivity : AppCompatActivity(), BaseView {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_trans_right_in, R.anim.anim_trans_right_out)
    }

    override fun onResume() {

        super.onResume()

    }

    override fun showProgress() {
        Utility.showProgressDialog(getViewActivity(), "")
    }

    override fun onPause() {
        super.onPause()

    }

    override fun hideProgress() {
        Utility.hideProgressDialog()
    }


    /**
     * Gets the fragment manager object of activity required for fragment transaction
     *
     * This method can be customised on the need of application,in which it returns [FragmentManager]
     *
     * @return object of [android.app.FragmentManager] or [FragmentManager]
     */
    fun getLocalFragmentManager(): FragmentManager {
        return this.supportFragmentManager
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