package com.dms.data.dto.response

import com.dms.domain.outing.response.OutingList
import com.dms.domain.outing.response.OutingListResponse
import com.google.gson.annotations.SerializedName

data class OutingListResponseData(
    val outings: List<OutingListData>
)

data class OutingListData(
    @SerializedName("outing_uuid")
    val outingUUID: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("start_time")
    val startTime: Int,
    @SerializedName("end_time")
    val endTime: Int,
    @SerializedName("outing_situation")
    val outingSituation: String,
    @SerializedName("outing_status")
    val outingStatus: String,
    @SerializedName("arrival_time")
    val arrivalTime: Int
)

fun OutingListResponseData.toDomainData(): OutingListResponse =
    OutingListResponse(this.outings.map{
        it.toDomainData()
    })

fun OutingListData.toDomainData(): OutingList =
    OutingList(
        this.outingUUID,
        this.place,
        this.reason,
        this.startTime,
        this.endTime,
        this.outingSituation,
        this.outingStatus,
        this.arrivalTime
    )