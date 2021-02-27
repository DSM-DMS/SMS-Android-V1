package com.dms.sms.feature.signup.model

import com.dms.domain.signup.entity.SignUpInfo

data class SignUpInfoModel(
    val authCode: Int,
    val studentId: String,
    val studentPw: String
)

fun SignUpInfo.toModel() : SignUpInfoModel =
    SignUpInfoModel(this.authCode,this.studentId,this.studentPw)

fun SignUpInfoModel.toDomain() : SignUpInfo =
    SignUpInfo(this.authCode,this.studentId,this.studentPw)