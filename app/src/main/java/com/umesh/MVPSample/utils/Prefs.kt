package com.umesh.MVPSample.utils

import android.content.Context
import android.content.SharedPreferences
import com.umesh.MVPSample.KotlinMVPSampleApplication
import com.umesh.MVPSample.response_models.UserInfo

class Prefs(context: Context) {

    val prefs: SharedPreferences = context.getSharedPreferences(Constants.SharedPref.PREF_FILE, 0);

    var userData: UserInfo
        get() = KotlinMVPSampleApplication.getGsonWithExpose().fromJson<UserInfo>(
                prefs.getString(Constants.SharedPrefKey.LOGGEDIN_USER, "{}")
                , UserInfo::class.java)
        set(value) = prefs.edit().putString(Constants.SharedPrefKey.LOGGEDIN_USER, KotlinMVPSampleApplication.getGsonWithExpose().toJson(value)).apply()


    var isRemember: Boolean
        get() = prefs.getBoolean(Constants.SharedPrefKey.IS_REMEMBER_ME, false)
        set(value) = prefs.edit().putBoolean(Constants.SharedPrefKey.IS_REMEMBER_ME, value).apply()

    var EmailRemember: String
        get() = prefs.getString(Constants.SharedPrefKey.USER_EMAIL, "")
        set(value) = prefs.edit().putString(Constants.SharedPrefKey.USER_EMAIL, value).apply()

    var PassRemember: String
        get() = prefs.getString(Constants.SharedPrefKey.USER_PASS, "")
        set(value) = prefs.edit().putString(Constants.SharedPrefKey.USER_PASS, value).apply()

}