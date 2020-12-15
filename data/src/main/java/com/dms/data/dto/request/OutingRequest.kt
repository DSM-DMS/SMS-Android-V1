package com.dms.data.dto.request

import com.dms.domain.auth.dto.LoginData
import com.dms.domain.outing.dto.OutingData
import com.google.gson.annotations.SerializedName

data class OutingRequest(
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

fun OutingData.toRequestData() : OutingRequest =
    OutingRequest(this.startTime, this.endTime, this.outingPlace, this.outingReason, this.outingSituation)