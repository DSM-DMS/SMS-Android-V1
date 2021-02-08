package com.dms.data.dto.response

import com.dms.domain.mypage.response.UserResponse
import com.google.gson.annotations.SerializedName

data class UserResponseData(
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("group")
    val group: Int,
    @SerializedName("student_number")
    val studentNumber: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("profile_uri")
    val profileUri: String
)

fun UserResponseData.toEntity(): UserResponse =
    UserResponse(
        this.grade,
        this.group,
        this.studentNumber,
        this.name,
        this.phoneNumber,
        this.profileUri
    )