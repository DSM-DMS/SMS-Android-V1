package com.dms.domain.outing.request

data class OutingRequest(
    val startTime: Int,
    val endTime: Int,
    val outingPlace: String,
    val outingReason: String,
    val outingSituation: String
)