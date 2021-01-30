package com.dms.domain.schedule.service

import com.dms.domain.base.Result
import com.dms.domain.schedule.entity.ScheduleDate
import com.dms.domain.schedule.entity.Schedules
import io.reactivex.Single

interface ScheduleService {
    fun getSchedule(scheduleDate: ScheduleDate) : Single<Result<Schedules>>
}