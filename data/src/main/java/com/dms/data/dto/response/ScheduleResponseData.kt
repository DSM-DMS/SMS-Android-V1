package com.dms.data.dto.response


import com.dms.data.util.convertTimeToDay
import com.dms.data.util.convertTimeToMonth
import com.dms.domain.schedule.entity.Schedule

data class ScheduleResponseData(
    val schedule_uuid: String,
    val start_date: Long,
    val end_date: Long,
    val detail: String
)

fun ScheduleResponseData.toEntity(): Schedule =
    Schedule(
        this.schedule_uuid,
        this.start_date.convertTimeToMonth().toInt(),
        this.start_date.convertTimeToDay().toInt(),
        this.end_date.convertTimeToMonth().toInt(),
        this.end_date.convertTimeToDay().toInt(),
        this.detail
    )
