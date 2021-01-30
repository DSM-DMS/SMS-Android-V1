package com.dms.domain.schedule.repository

import com.dms.domain.schedule.entity.ScheduleDate
import com.dms.domain.schedule.entity.Schedules
import io.reactivex.Single

interface ScheduleRepository {
    fun getSchedule(scheduleDate: ScheduleDate) : Single<Schedules>
}