package com.dms.domain.schedule.service

import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.schedule.entity.ScheduleDate
import com.dms.domain.schedule.entity.Schedules
import com.dms.domain.schedule.repository.ScheduleRepository
import io.reactivex.Single

class ScheduleServiceImpl(private val scheduleRepository: ScheduleRepository, private val errorHandler: ErrorHandler) : ScheduleService {
    override fun getSchedule(scheduleDate: ScheduleDate) : Single<Result<Schedules>> =
        scheduleRepository.getSchedule(scheduleDate).toResult(errorHandler)


}