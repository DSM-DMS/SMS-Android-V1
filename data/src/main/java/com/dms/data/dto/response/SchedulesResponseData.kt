package com.dms.data.dto.response

import com.dms.domain.schedule.entity.Schedules

data class SchedulesResponseData(val schedules : List<ScheduleResponseData>)


fun SchedulesResponseData.toEntity() : Schedules =
    Schedules(schedules.map {
        it.toEntity()
    })
