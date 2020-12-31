package com.dms.domain.mypage.response

data class UserResponse(
    val grade: Int,
    val group: Int,
    val studentNumber: Int,
    val name: String,
    val phoneNumber: String,
    val profileUri: String
)