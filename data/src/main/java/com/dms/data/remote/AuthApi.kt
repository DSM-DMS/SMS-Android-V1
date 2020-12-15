package com.dms.data.remote

import com.dms.data.dto.request.LoginData
import com.dms.data.dto.response.LoginResponseData
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface AuthApi : Api {
    @POST("v1/login/student")
    fun login(@Body loginData: LoginData): Single<LoginResponseData>

    @PUT("v1/students/uuid/{student_uuid}/password")
    fun changePassword(@Path("student_uuid") studentUUID: String): Completable

}