package com.dms.sms.feature.login.model

import com.dms.domain.auth.request.LoginRequest

data class LoginModel(val id: String, val password: String)

fun LoginModel.toDomain(): LoginRequest {
    return LoginRequest(this.id, this.password)
}