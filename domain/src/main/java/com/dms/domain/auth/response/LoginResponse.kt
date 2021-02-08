package com.dms.domain.auth.response

data class LoginResponse(
    val accessToken: String,
    val studentUUID: String
)