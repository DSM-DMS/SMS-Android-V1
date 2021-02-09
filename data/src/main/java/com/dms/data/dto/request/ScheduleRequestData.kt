package com.dms.data.dto.request

import com.dms.domain.schedule.entity.ScheduleDate

data class ScheduleRequestData(val year : Int, val month : Int)

fun ScheduleDate.toData() : ScheduleRequestData =
    ScheduleRequestData(this.year, this.month)