package com.dms.data.remote

import com.dms.data.dto.request.OutingData
import com.dms.data.dto.response.OutingResponseData
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface OutingApi {
    @POST("v1/outings")
    fun createOuting(@Body outingData: OutingData) : Single<OutingResponseData>
}