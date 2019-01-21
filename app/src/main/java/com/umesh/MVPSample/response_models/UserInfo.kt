package com.umesh.MVPSample.response_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserInfo : Serializable {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("role_id")
    @Expose
    var roleId: Int? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("api_key")
    @Expose
    var apiKey: String? = null
    @SerializedName("api_plain_key")
    @Expose
    var apiPlainKey: String? = null
    @SerializedName("firstname")
    @Expose
    var firstname: String? = null
    @SerializedName("lastname")
    @Expose
    var lastname: String? = null
    @SerializedName("profile_image")
    @Expose
    var profileImage: String? = null
    @SerializedName("user_header_photo")
    @Expose
    var userHeaderPhoto: String? = null
    @SerializedName("phone_no")
    @Expose
    var phoneNo: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("verification_code")
    @Expose
    var verificationCode: String? = null
    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("isDeleted")
    @Expose
    var isDeleted: Int? = null
    @SerializedName("created")
    @Expose
    var created: String? = null
    @SerializedName("modified")
    @Expose
    var modified: String? = null

}