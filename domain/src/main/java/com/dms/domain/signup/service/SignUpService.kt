package com.dms.domain.signup.service

import com.dms.domain.signup.entity.NoAccountStudent
import com.dms.domain.signup.entity.SignUpInfo
import io.reactivex.Single
import com.dms.domain.base.Result
interface SignUpService {
    fun getNoAccountStudentInfo(authCode : Int) : Single<Result<NoAccountStudent>>

    fun signUp(signUpInfo: SignUpInfo) : Single<Result<Unit>>
}