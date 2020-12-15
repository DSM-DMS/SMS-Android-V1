package com.dms.data.remote

import com.dms.data.dto.request.OutingRequest
import com.dms.data.dto.response.LoginResponse
import com.dms.data.dto.response.OutingResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface OutingApi {
    @POST("v1/outings")
    fun createOuting(@Body outingRequest: OutingRequest) : Single<OutingResponse>
}