package com.dms.data.dto.request

import com.dms.domain.auth.dto.LoginData
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("student_id")
    val studentId : String,
    @SerializedName("student_pw")
    val studentPw : String
    )
fun LoginData.toRequestData() : LoginRequest =
    LoginRequest(this.id, this.password)