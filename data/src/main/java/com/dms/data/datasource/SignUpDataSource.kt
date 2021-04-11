package com.dms.data.datasource

import com.dms.data.dto.request.SignUpRequestData
import com.dms.data.dto.response.VerificationNumberResponseData
import io.reactivex.Completable
import io.reactivex.Single

interface SignUpDataSource {
    fun getNoAccountStudentInfo(authCode: Int) : Single<VerificationNumberResponseData>

    fun signUp(signUpInfo : SignUpRequestData) : Completable
}