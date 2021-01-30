package com.dms.domain.schedule.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.schedule.entity.ScheduleDate
import com.dms.domain.schedule.entity.Schedules
import com.dms.domain.schedule.entity.calculateDate
import com.dms.domain.schedule.service.ScheduleService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetScheduleUseCase(private val scheduleService: ScheduleService, disposable: CompositeDisposable) : UseCase<ScheduleDate, Result<Schedules>>(disposable) {
    override fun buildUseCase(data: ScheduleDate): Single<Result<Schedules>> =
        scheduleService.getSchedule(data.calculateDate())
}