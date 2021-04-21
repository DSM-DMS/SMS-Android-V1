package com.dms.sms.feature.login.model

import com.dms.domain.auth.entity.LoggedInUser

data class LoggedInUserModel(
    val accessToken: String,
    val studentUUID: String,
    val isAutoLoginChecked: Boolean
)

fun LoggedInUserModel.toEntity(): LoggedInUser =
    LoggedInUser(this.accessToken, this.studentUUID, this.isAutoLoginChecked)