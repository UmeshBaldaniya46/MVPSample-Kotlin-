package com.umesh.MVPSample

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.umesh.MVPSample.APIHelper.RequestInterceptor
import com.umesh.MVPSample.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class KotlinMVPSampleApplication : Application() {

    //Instance of Application Class
    var mInstance: KotlinMVPSampleApplication? = null

    //Instance of Gson
    lateinit var mGson: Gson

    //Instance of Gson without Expose
    lateinit var mGsonExcludeExpose: Gson

    //Instance of OkHttpclient
    private var client: OkHttpClient? = null

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    /**
     * Method for get current instance of Application
     *
     * @return
     */

    fun getAppInstance(): KotlinMVPSampleApplication? {
        return mInstance
    }

    /**
     * Method for get synchronized instance of Application
     *
     * @return
     */
    @Synchronized
    fun getInstance(): KotlinMVPSampleApplication? {
        return mInstance
    }

    /**
     * Method for Create OkHttpClient
     *
     * @return
     */
    fun getClient(): OkHttpClient {

        if (client == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val m1RequestInterceptor = RequestInterceptor(this)
            client = OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .addInterceptor(m1RequestInterceptor)
                    .build()
        }
        return client as OkHttpClient
    }

    /**
     * Method for get data in Preference
     *
     * @param name
     * @return
     */

    fun getSharedPreference(name: String): SharedPreferences {
        return getAppInstance()!!.getSharedPreferences(name, Context.MODE_PRIVATE)
    }


    /**
     * Method for get SharedPreferences for edit
     *
     * @param name
     * @return
     */
    fun getSharedPreferenceEditor(name: String): SharedPreferences.Editor {
        val preferences = getAppInstance()!!.getSharedPreferences(name, Context.MODE_PRIVATE)
        return preferences.edit()
    }

    /**
     * Method for Gson With Expose
     *
     * @return
     */
    companion object {
        fun getGsonWithExpose(): Gson {
            val mGson: Gson
            mGson = GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()
            return mGson
        }
    }

    /**
     * Method for Gson Without Expose
     *
     * @return
     */
    fun getGsonWithoutExpose(): Gson {
        mGsonExcludeExpose = GsonBuilder().setLenient().create()
        return mGsonExcludeExpose
    }


    /**
     * Method for save Login User data in to Preferences
     *
     * @return
     */
    fun setShowCas(id: String) {
        val sharedPrefEditor = getSharedPreferenceEditor(Constants.SharedPref.PREF_FILE)
        sharedPrefEditor.putBoolean(id, true).apply()
    }

    fun getShowCase(id: String): Boolean? {
        val sharedPref = getSharedPreference(Constants.SharedPref.PREF_FILE)
        return sharedPref.getBoolean(id, false)
    }

    /**
     * Reset OkHttpclient
     */

    fun resetClient() {
        client = null
        getClient()
    }
}