package com.dms.sms.feature.timetable.model

import com.dms.domain.timetable.request.TimeTableDate

data class TimeTableDateModel(
    val year: String,
    val month: String,
    val day: String
)

fun TimeTableDateModel.toDomain(): TimeTableDate =
    TimeTableDate(
        this.year,
        this.month,
        this.day
    )