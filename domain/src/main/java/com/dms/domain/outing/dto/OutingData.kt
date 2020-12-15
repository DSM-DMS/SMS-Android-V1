package com.dms.domain.outing.dto

data class OutingData(
    val startTime: Int,
    val endTime: Int,
    val outingPlace: String,
    val outingReason: String,
    val outingSituation: String
)