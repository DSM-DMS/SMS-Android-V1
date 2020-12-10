package com.dms.sms.login.model

import com.dms.domain.auth.dto.LoginData

data class LoginModel(val id : String, val password : String)

fun LoginModel.toDomain() : LoginData {
    return LoginData(this.id, this.password)
}