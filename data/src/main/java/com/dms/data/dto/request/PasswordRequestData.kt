package com.dms.data.dto.request

import com.dms.domain.mypage.request.PasswordRequest
import com.google.gson.annotations.SerializedName

data class PasswordRequestData(
    @SerializedName("current_pw")
    val currentPw: String,
    @SerializedName("revision_pw")
    val newPw: String
)

fun PasswordRequest.toData(): PasswordRequestData =
    PasswordRequestData(
        this.currentPw,
        this.newPw
    )