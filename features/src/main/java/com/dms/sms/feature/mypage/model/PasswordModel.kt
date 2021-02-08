package com.dms.sms.feature.mypage.model

import com.dms.domain.mypage.request.PasswordRequest

data class PasswordModel(
    val currentPw: String,
    val newPw: String
)

fun PasswordModel.toDomain(): PasswordRequest =
    PasswordRequest(
        this.currentPw,
        this.newPw
    )