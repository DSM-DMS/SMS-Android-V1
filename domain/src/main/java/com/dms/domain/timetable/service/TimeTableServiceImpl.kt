package com.dms.domain.timetable.service

import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.timetable.repository.TimeTableRepository
import com.dms.domain.timetable.request.TimeTableDate
import com.dms.domain.timetable.response.TimeTableResponse
import io.reactivex.Single

class TimeTableServiceImpl(
    private val timeTableRepository: TimeTableRepository,
    private val errorHandler: ErrorHandler
) : TimeTableService {
    override fun getTimeTable(timeTableDate: TimeTableDate): Single<Result<TimeTableResponse>> =
        timeTableRepository.getTimeTable(timeTableDate.year, timeTableDate.month, timeTableDate.day).toResult(errorHandler)
}