package com.dms.data.repository

import com.dms.data.datasource.TimeTableDataSource
import com.dms.data.dto.response.toEntity
import com.dms.domain.timetable.repository.TimeTableRepository
import com.dms.domain.timetable.response.TimeTableListResponse
import io.reactivex.Single

class TimeTableRepositoryImpl(private val timeTableDataSource: TimeTableDataSource): TimeTableRepository {
    override fun getTimeTable(year: String, month: String, day: String): Single<TimeTableListResponse> =
        timeTableDataSource.getTimeTable(year, month, day).map { it.toEntity() }
    }