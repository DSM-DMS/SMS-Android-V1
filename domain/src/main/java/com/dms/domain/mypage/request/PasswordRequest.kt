package com.dms.domain.mypage.request

data class PasswordRequest(
    val currentPw: String,
    val newPw: String
)