package com.dms.data.dto.response

import com.dms.domain.auth.dto.LoginResponseData

data class LoginResponse(
    val student_uuid: String,
    val access_token : String
    )

fun LoginResponse.toDomainData() : LoginResponseData =
    LoginResponseData(this.student_uuid, this.access_token)