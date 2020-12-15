package com.dms.data.dto.request

import com.dms.domain.auth.request.LoginRequest
import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("student_id")
    val studentId : String,
    @SerializedName("student_pw")
    val studentPw : String
    )
fun LoginRequest.toRequestData() : LoginRequest =
    LoginRequest(this.id, this.password)