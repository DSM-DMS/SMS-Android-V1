package com.dms.data.dto.response

import com.dms.domain.account.entity.Student

data class StudentResponseData(
    val grade: Int,
    val group: Int,
    val student_number: Int,
    val name : String,
    val phone_number : String,
    val profile_uri : String,
    val student_uuid : String,
)

fun StudentResponseData.toEntity() : Student =
    Student(this.grade, this.group, this.student_number, this.name, this.phone_number, this.profile_uri)