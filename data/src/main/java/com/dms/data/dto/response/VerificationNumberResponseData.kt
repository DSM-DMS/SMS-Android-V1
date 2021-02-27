package com.dms.data.dto.response

import com.dms.domain.signup.entity.NoAccountStudent
import com.google.gson.annotations.SerializedName

data class VerificationNumberResponseData(
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("group")
    val group: Int,
    @SerializedName("student_number")
    val studentNumber: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)

fun VerificationNumberResponseData.toEntity(): NoAccountStudent =
    NoAccountStudent(this.grade, this.group, this.studentNumber, this.name, this.phoneNumber)