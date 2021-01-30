package com.dms.data.datasource

import com.dms.data.dto.request.ScheduleRequestData
import com.dms.data.dto.response.SchedulesResponseData
import com.dms.data.remote.ScheduleApi
import io.reactivex.Single

class ScheduleDataSourceImpl(private val scheduleApi: ScheduleApi) : ScheduleDataSource {
    override fun getSchedule(scheduleRequestData: ScheduleRequestData): Single<SchedulesResponseData> =
        scheduleApi.getSchedule(scheduleRequestData.year, scheduleRequestData.month)
}