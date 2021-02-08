package com.dms.data.datasource

import com.dms.data.dto.response.TimeTableResponseData
import io.reactivex.Single

interface TimeTableDataSource {
    fun getTimeTable(year: String, month: String, day: String): Single<TimeTableResponseData>
}