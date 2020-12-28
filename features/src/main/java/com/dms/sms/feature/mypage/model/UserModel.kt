package com.dms.sms.feature.mypage.model

import com.dms.domain.mypage.response.UserResponse

data class UserModel(
    val grade: Int,
    val group: Int,
    val studentNumber: Int,
    val name: String,
    val phoneNumber: String,
    val profileUri: String
){
    val stdNum: String
    get() = "$grade$group$studentNumber"
}

fun UserResponse.toModel(): UserModel =
    UserModel(
        this.grade,
        this.group,
        this.studentNumber,
        this.name,
        this.phoneNumber,
        this.profileUri,
    )