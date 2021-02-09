package com.dms.data.remote

import com.dms.data.dto.response.SchedulesResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleApi {
    @GET("v1/schedules/years/{year}/months/{month}")
    fun getSchedule(@Path("year") year : Int, @Path("month") month : Int) : Single<SchedulesResponseData>
}