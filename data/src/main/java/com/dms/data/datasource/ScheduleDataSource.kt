package com.dms.data.datasource

import com.dms.data.dto.request.ScheduleRequestData
import com.dms.data.dto.response.SchedulesResponseData
import io.reactivex.Single

interface ScheduleDataSource {
    fun getSchedule(scheduleRequestData: ScheduleRequestData) : Single<SchedulesResponseData>
}