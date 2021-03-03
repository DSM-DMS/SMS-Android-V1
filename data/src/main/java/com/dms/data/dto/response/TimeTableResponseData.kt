package com.dms.data.dto.response

import com.dms.domain.timetable.response.TimeTableListResponse
import com.dms.domain.timetable.response.TimeTableResponse
import com.google.gson.annotations.SerializedName

data class TimeTableListResponseData(
    @SerializedName("time_tables")
    val timeTables: List<TimeTableResponseData>
)

data class TimeTableResponseData(
    val time1: String,
    val time2: String,
    val time3: String,
    val time4: String,
    val time5: String,
    val time6: String,
    val time7: String
)

fun TimeTableResponseData.toEntity(): TimeTableResponse =
    TimeTableResponse(
        this.time1,
        this.time2,
        this.time3,
        this.time4,
        this.time5,
        this.time6,
        this.time7
    )

fun TimeTableListResponseData.toEntity(): TimeTableListResponse =
    TimeTableListResponse(
        this.timeTables.map { it.toEntity() }
    )