package com.dms.domain.outing.response

data class OutingListResponse(
    val outing: List<OutingList>
)

data class OutingList(
    val outingUUID: String,
    val place: String,
    val reason: String,
    val startTime: Int,
    val endTime: Int,
    val outingSituation: String,
    val outingStatus: String
)