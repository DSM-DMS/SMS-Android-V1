package com.dms.sms.feature.signup.model

import com.dms.domain.signup.entity.NoAccountStudent

data class NoAccountStudentModel(
    val grade: String,
    val group: Int,
    var studentNumber: String,
    val name: String,
    val phoneNumber: String
)

fun NoAccountStudent.toModel() : NoAccountStudentModel =
    NoAccountStudentModel(this.grade.toString(),this.group,this.studentNumber.toString(),this.name,this.phoneNumber)