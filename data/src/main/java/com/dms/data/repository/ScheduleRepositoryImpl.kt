package com.dms.data.repository

import com.dms.data.datasource.ScheduleDataSource
import com.dms.data.dto.request.toData
import com.dms.data.dto.response.toEntity
import com.dms.domain.schedule.entity.ScheduleDate
import com.dms.domain.schedule.entity.Schedules
import com.dms.domain.schedule.repository.ScheduleRepository
import io.reactivex.Single

class ScheduleRepositoryImpl(private val scheduleDataSource: ScheduleDataSource) : ScheduleRepository {
    override fun getSchedule(scheduleDate: ScheduleDate): Single<Schedules> =
        scheduleDataSource.getSchedule(scheduleDate.toData()).map {
            it.toEntity()
        }
}