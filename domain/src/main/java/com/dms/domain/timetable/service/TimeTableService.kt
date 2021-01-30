package com.dms.domain.timetable.service

import com.dms.domain.base.Result
import com.dms.domain.timetable.request.TimeTableDate
import com.dms.domain.timetable.response.TimeTableResponse
import io.reactivex.Single

interface TimeTableService {
    fun getTimeTable(timeTableDate: TimeTableDate): Single<Result<TimeTableResponse>>
}