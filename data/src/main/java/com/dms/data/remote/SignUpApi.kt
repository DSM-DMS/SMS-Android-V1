package com.dms.data.remote

import com.dms.data.dto.request.SignUpRequestData
import com.dms.data.dto.response.VerificationNumberResponseData
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface SignUpApi {
    @GET("v1/students/auth-code/{auth_code}")
    fun getNoAccountStudentInfo(@Path("auth_code") authCode : Int) : Single<VerificationNumberResponseData>

    @POST("v1/students/with-code")
    fun signUp(@Body signUpInfo : SignUpRequestData) : Completable
}