package com.dms.data.datasource

import com.dms.data.dto.response.TimeTableListResponseData
import com.dms.data.remote.TimeTableApi
import io.reactivex.Single

class TimeTableDataSourceImpl(private val timeTableApi: TimeTableApi): TimeTableDataSource {
    override fun getTimeTable(year: String,month: String,day: String): Single<TimeTableListResponseData> =
        timeTableApi.getTimeTable(year, month, day,5)
}