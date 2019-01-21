package com.umesh.MVPSample.utils

class Constants {


    interface SharedPref {
        companion object {
            val PREF_FILE = "SampleApp"
        }
    }

    interface SharedPrefKey {
        companion object {

            val IS_REMEMBER_ME = "isRememberMe"
            val USER_EMAIL = "userEmail"
            val USER_PASS = "userPassword"
            val LOGGEDIN_USER = "authorization"
        }
    }

    interface ApiMethods {

        companion object {

            const val GET_LOGIN = "users/login.json"
           }
    }

    interface ApiHeaders {
        companion object {

            val API_TYPE_JSON = "application/json"
            val AUTHORIZATION = "authorization"
        }
    }

    interface PHOTO_CONST {
        companion object {
            val PHOTOS = "Photos"
            val CAMERA = "Camera"
            val TYPE_PHOTO_PICK_FROM_CAMERA = 1
            val TYPE_PHOTO_PICK_FROM_FILE = 2
            val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
        }
    }

}