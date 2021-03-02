package com.dms.domain.auth.response

data class LoginResponse(
    val studentUUID: String,
    val accessToken: String
    )