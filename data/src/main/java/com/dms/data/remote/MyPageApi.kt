package com.dms.data.remote

import com.dms.data.dto.response.UserResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageApi {
    @GET("/v1/students/uuid/{student_uuid}")
    fun getUserProfile(@Path("student_uuid") studentUUID: String): Single<UserResponseData>
}