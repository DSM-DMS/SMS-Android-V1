package com.dms.domain.outing.response

data class DetailOutingResponse(
    val place: String,
    val startTime: String,
    val endTime: String,
    val outingStatus: String,
    val name: String,
    val grade: Int,
    val group: Int,
    val number: Int,
    val profileUri: String
)