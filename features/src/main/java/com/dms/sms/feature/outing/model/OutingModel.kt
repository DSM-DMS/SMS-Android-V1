package com.dms.sms.feature.outing.model

import com.dms.domain.outing.request.OutingRequest

data class OutingModel(
    val startTime: Int,
    val endTime: Int,
    val outingPlace: String,
    val outingReason: String,
    val outingSituation: String
)

fun OutingModel.toDomain(): OutingRequest {
    return OutingRequest(
        this.startTime,
        this.endTime,
        this.outingPlace,
        this.outingReason,
        this.outingSituation
    )
}