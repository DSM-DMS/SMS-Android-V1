package com.dms.data.dto.request

data class StudentRequestData(
    val grade: Int,
    val group: Int,
    val student_number: Int,
    val name : String,
    val phone_number : String,
    val profile_uri : String,
)
