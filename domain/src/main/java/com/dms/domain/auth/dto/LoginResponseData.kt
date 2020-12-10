package com.dms.domain.auth.dto

data class LoginResponseData(
    private val accessToken: String,
    private val studentUUID: String
)