package com.dms.sms.feature.outing.model

import com.dms.domain.outing.request.OutingApplyRequest

data class OutingApplyModel(
    val startTime: Int,
    val endTime: Int,
    val outingPlace: String,
    val outingReason: String,
    val outingSituation: String
)

fun OutingApplyModel.toDomain(): OutingApplyRequest {
    return OutingApplyRequest(
        this.startTime,
        this.endTime,
        this.outingPlace,
        this.outingReason,
        this.outingSituation
    )
}