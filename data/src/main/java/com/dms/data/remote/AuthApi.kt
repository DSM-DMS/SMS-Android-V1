package com.dms.data.remote

import com.dms.data.dto.request.LoginRequestData
import com.dms.data.dto.response.LoginResponseData
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface AuthApi  {
    @POST("v1/login/student")
    fun login(@Body loginRequestData : LoginRequestData): Single<LoginResponseData>
}