package com.dms.data.remote

import com.dms.data.dto.request.OutingData
import com.dms.data.dto.response.DetailOutingResponseData
import com.dms.data.dto.response.OutingListResponseData
import com.dms.data.dto.response.OutingResponseData
import com.dms.data.dto.response.SearchPlaceListResponseData
import io.reactivex.Single
import retrofit2.http.*

interface OutingApi {
    @POST("v1/outings")
    fun createOuting(@Body outingData: OutingData): Single<OutingResponseData>

    @GET("/v1/students/uuid/{student_uuid}/outings")
    fun getOutingList(@Path("student_uuid") studentUUID: String): Single<OutingListResponseData>

    @GET("/v1/outings/uuid/{outing_uuid}/card")
    fun getDetailOuting(@Path("outing_uuid") outingUUID: String): Single<DetailOutingResponseData>

    @GET("/naver-open-api/search/local")
    fun getPlaceList(@Query("keyword") keyword: String): Single<SearchPlaceListResponseData>
}