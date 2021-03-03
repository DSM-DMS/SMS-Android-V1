package com.dms.domain.timetable.repository

import com.dms.domain.timetable.response.TimeTableListResponse
import io.reactivex.Single

interface TimeTableRepository {
    fun getTimeTable(year: String, month: String, day: String): Single<TimeTableListResponse>
}