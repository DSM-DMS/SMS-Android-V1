package com.dms.sms.feature.schedule.model

import com.dms.domain.schedule.entity.Schedule

data class ScheduleModel(
    val scheduleUUID: String,
    val startMonth: Int,
    val startDay: Int,
    val endMonth: Int,
    val endDay: Int,
    val detail: String,
    var datePosition: Int = -1
)

fun Schedule.toModel(): ScheduleModel =
    ScheduleModel(
        this.scheduleUUID,
        this.startMonth,
        this.startDay,
        this.endMonth,
        this.endDay,
        this.detail
    )
