package com.dms.data.dto.response

import com.dms.domain.auth.response.LoginResponse

data class LoginResponseData(
    val student_uuid: String,
    val access_token : String
    )

fun LoginResponseData.toDomainData() : LoginResponse =
    LoginResponse(this.student_uuid, this.access_token)