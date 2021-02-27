package com.dms.domain.account.entity

data class Student(
     val grade: Int,
     val group: Int,
     val studentNumber: Int,
     val name: String,
     val phoneNumber: String,
     val profileImage: String,
     val studentUUID: String,
     val parentStatus: String
)