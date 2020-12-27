package com.dms.data.dto.request

import com.dms.domain.auth.request.LoginRequest
import com.google.gson.annotations.SerializedName

data class LoginRequestData(
    @SerializedName("student_id")
    val studentId : String,
    @SerializedName("student_pw")
    val studentPw : String
    )
fun LoginRequest.toData() : LoginRequestData =
    LoginRequestData(this.id, this.password)