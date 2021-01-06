package com.dms.data.datasource

import com.dms.data.dto.response.TimeTableResponseData
import com.dms.data.remote.TimeTableApi
import io.reactivex.Single

class TimeTableDataSourceImpl(private val timeTableApi: TimeTableApi): TimeTableDataSource {
    override fun getTimeTable(year: String,month: String,day: String): Single<TimeTableResponseData> =
        timeTableApi.getTimeTable(year, month, day)
}