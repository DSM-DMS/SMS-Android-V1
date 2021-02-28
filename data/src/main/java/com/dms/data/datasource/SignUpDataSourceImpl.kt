package com.dms.data.datasource

import com.dms.data.dto.request.SignUpRequestData
import com.dms.data.dto.response.StudentResponseData
import com.dms.data.dto.response.VerificationNumberResponseData
import com.dms.data.remote.SignUpApi
import io.reactivex.Completable
import io.reactivex.Single

class SignUpDataSourceImpl(private val signUpApi: SignUpApi) : SignUpDataSource{
    override fun getNoAccountStudentInfo(authCode: Int) : Single<VerificationNumberResponseData>
        = signUpApi.getNoAccountStudentInfo(authCode)

    override fun signUp(signUpInfo : SignUpRequestData) : Completable =
        signUpApi.signUp(signUpInfo)

}