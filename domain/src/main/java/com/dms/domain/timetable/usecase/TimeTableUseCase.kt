package com.dms.domain.timetable.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.timetable.request.TimeTableDate
import com.dms.domain.timetable.response.TimeTableResponse
import com.dms.domain.timetable.service.TimeTableService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class TimeTableUseCase(private val timeTableService: TimeTableService, private val disposable: CompositeDisposable):
    UseCase<TimeTableDate, Result<TimeTableResponse>>(disposable){
    override fun buildUseCase(data: TimeTableDate): Single<Result<TimeTableResponse>> =
        timeTableService.getTimeTable(data)
}