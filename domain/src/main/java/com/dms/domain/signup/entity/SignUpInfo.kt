package com.dms.domain.signup.entity

data class SignUpInfo(
    val authCode: Int,
    val studentId: String,
    val studentPw: String
)