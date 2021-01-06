package com.dms.data.remote

import com.dms.data.dto.response.TimeTableResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TimeTableApi {
    @GET("/v1/time-tables/years/{year}/months/{month}/days/{day}")
    fun getTimeTable(
        @Path("year") year: String,
        @Path("month") month: String,
        @Path("day") day: String
    ): Single<TimeTableResponseData>
}