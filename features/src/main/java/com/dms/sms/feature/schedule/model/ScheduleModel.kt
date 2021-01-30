package com.dms.sms.feature.schedule.model

import com.dms.domain.schedule.entity.Schedule

data class ScheduleModel(val scheduleUUID : String, val startDate : Int, val endDate : Int, val detail : String, var datePosition : Int = -1)

fun Schedule.toModel() : ScheduleModel =
    ScheduleModel(this.scheduleUUID, this.startDate, this.endDate, this.detail)
