package com.dms.data.remote

import com.dms.data.dto.response.TimeTableListResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TimeTableApi {
    @GET("/v1/time-tables/years/{year}/months/{month}/days/{day}")
    fun getTimeTable(
        @Path("year") year: String,
        @Path("month") month: String,
        @Path("day") day: String,
        @Query("count") count: Int
    ): Single<TimeTableListResponseData>
}