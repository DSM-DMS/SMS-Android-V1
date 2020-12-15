package com.dms.data.dto.request

import com.dms.domain.outing.request.OutingRequest
import com.google.gson.annotations.SerializedName

data class OutingData(
    @SerializedName("start_time")
    val startTime: Int,
    @SerializedName("end_time")
    val endTime: Int,
    @SerializedName("place")
    val outingPlace: String,
    @SerializedName("reason")
    val outingReason: String,
    @SerializedName("situation")
    val outingSituation: String
)

fun OutingRequest.toData() : OutingData =
    OutingData(startTime, endTime, outingPlace, outingReason, outingSituation)