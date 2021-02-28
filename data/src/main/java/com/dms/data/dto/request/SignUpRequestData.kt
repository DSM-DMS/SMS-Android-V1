package com.dms.data.dto.request

import com.dms.domain.signup.entity.SignUpInfo
import com.google.gson.annotations.SerializedName

data class SignUpRequestData(
    @SerializedName("auth_code")
    val authCode : Int,
    @SerializedName("student_id")
    val studentId : String,
    @SerializedName("student_pw")
    val studentPw : String)

fun SignUpInfo.toData() : SignUpRequestData =
    SignUpRequestData(this.authCode,this.studentId,this.studentPw)