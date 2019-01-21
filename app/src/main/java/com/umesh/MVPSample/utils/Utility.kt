package com.umesh.MVPSample.utils

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.umesh.MVPSample.R
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.regex.Pattern

class Utility {

    companion object {

        private var progressDialog: Dialog? = null
        val EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\._%\\-\\+]{1,256}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+")

        fun setSingInAnimation(viewOnAnimation: View, parentView: View, progressView: View) {

            var width = parentView.width;
            width -= viewOnAnimation.width;
            val anim = TranslateAnimation(0f, width.toFloat(), 0f, 0f)
            anim.duration = 600
            anim.fillAfter = true
            viewOnAnimation.startAnimation(anim)

            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {

                }

                override fun onAnimationEnd(animation: Animation) {
                    viewOnAnimation.visibility = View.GONE
                    progressView.visibility = View.VISIBLE

                }

                override fun onAnimationRepeat(animation: Animation) {

                }
            })
        }


        /**
         * This method is used to show progress indicator dialog with message when
         * web service is called.
         */
        fun showProgressDialog(context: Context?, message: String) {

            if (context != null && !(context as Activity).isFinishing) {
                if (progressDialog == null || !progressDialog!!.isShowing()) {
                    progressDialog = Dialog(context)
                    progressDialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    progressDialog!!.setContentView(R.layout.custom_progressbar)
                    val tvMessage = progressDialog!!.findViewById(R.id.txtMessage) as TextView
                    if (message != "") {
                        tvMessage.text = message
                    }
                    progressDialog!!.setCancelable(false)
                    if (!context.isFinishing)
                        progressDialog!!.show()
                }
            } else {
                Log.e(TAG, context!!.toString() + " Context Null")

            }
        }


        /**
         * Method Name: hideProgressDialog Created By: dev458 Created Date:
         * 28/March/2013 Modified By: Modified Date: Purpose: This method is used to
         * hide progress dialog and destroy progress dialog instance .
         */
        fun hideProgressDialog() {
            try {
                if (progressDialog != null && progressDialog!!.isShowing()) {
                    progressDialog!!.dismiss()
                }
            } catch (throwable: Throwable) {

            } finally {
                progressDialog = null
            }
        }

        fun getRequest(reqString: String): RequestBody {
            val mediaType = MediaType.parse(Constants.ApiHeaders.API_TYPE_JSON)
            return RequestBody.create(mediaType, reqString)
        }


        fun hideSoftKeyboard(activity: Activity) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            if (inputMethodManager.isAcceptingText) {
                inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            }
        }

        fun hideKeyboard(aContext: Activity?) {
            if (aContext != null) {
                val im = aContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(aContext.window.decorView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }


        fun checkEmail(email: String): Boolean {
            return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
        }


        fun showSnackBar(view: View, message: String, time: Int, isTypeError: Boolean, context: Context) {

            val snackbar = Snackbar.make(view, message, time)
            val snackBarView = snackbar.view
            val snackTextView = snackBarView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
            snackTextView.maxLines = 4
            if (isTypeError) {
                snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorRed))
            } else {
                snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.GradientColorEnd))
            }
            snackbar.show()
        }
    }

}