package com.dms.sms.feature.schedule.model

import com.dms.domain.schedule.entity.ScheduleDate

data class ScheduleDateModel(val year: Int, val month: Int)

fun ScheduleDateModel.toEntity() =
    ScheduleDate(this.year, this.month)
