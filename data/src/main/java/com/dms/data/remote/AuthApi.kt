package com.dms.data.remote

import com.dms.data.dto.request.LoginRequest
import com.dms.data.dto.response.LoginResponse
import com.dms.data.dto.response.StudentResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface AuthApi {
    @POST("v1/login/student")
    fun login(@Body loginRequest: LoginRequest) : Single<LoginResponse>

    @PUT("v1/students/uuid/{student_uuid}/password")
    fun changePassword(@Path("student_uuid") studentUUID : String) : Completable

}