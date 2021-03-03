package com.dms.data.datasource

import com.dms.data.dto.response.TimeTableListResponseData
import io.reactivex.Single

interface TimeTableDataSource {
    fun getTimeTable(year: String, month: String, day: String): Single<TimeTableListResponseData>
}