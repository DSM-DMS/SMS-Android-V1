package com.dms.data.remote

import com.dms.data.dto.request.PasswordRequestData
import com.dms.data.dto.response.UserResponseData
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface MyPageApi {
    @GET("/v1/students/uuid/{student_uuid}")
    fun getUserProfile(@Path("student_uuid") studentUUID: String): Single<UserResponseData>

    @PUT("/v1/students/uuid/{student_uuid}/password")
    fun changePw(@Path("student_uuid") studentUUID: String, @Body pw: PasswordRequestData): Completable
}