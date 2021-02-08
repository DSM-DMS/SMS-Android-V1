package com.dms.data.dto.response

import com.dms.domain.outing.response.DetailOutingResponse
import com.google.gson.annotations.SerializedName

data class DetailOutingResponseData(
    @SerializedName("place")
    val place: String,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("outing_status")
    val outingStatus: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("group")
    val group: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("profile_uri")
    val profileUri: String
)

fun DetailOutingResponseData.toDomainData(): DetailOutingResponse =
    DetailOutingResponse(
        this.place,
        this.startTime,
        this.endTime,
        this.outingStatus,
        this.name,
        this.grade,
        this.group,
        this.number,
        this.profileUri
    )