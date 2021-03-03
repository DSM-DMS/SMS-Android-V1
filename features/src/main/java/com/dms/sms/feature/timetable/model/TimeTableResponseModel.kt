package com.dms.sms.feature.timetable.model

import com.dms.domain.timetable.response.TimeTableListResponse
import com.dms.domain.timetable.response.TimeTableResponse

data class TimeTableListResponseModel(
    val timeTables: List<TimeTableResponseModel>
)

data class TimeTableResponseModel(
    val time1: String,
    val time2: String,
    val time3: String,
    val time4: String,
    val time5: String,
    val time6: String,
    val time7: String
)

fun TimeTableResponse.toModel(): TimeTableResponseModel =
    TimeTableResponseModel(
        this.time1,
        this.time2,
        this.time3,
        this.time4,
        this.time5,
        this.time6,
        this.time7
    )

fun TimeTableListResponse.toModel(): TimeTableListResponseModel =
    TimeTableListResponseModel(
        this.timeTables.map { it.toModel() }
    )