package com.dms.domain.signup.repository

import com.dms.domain.signup.entity.NoAccountStudent
import com.dms.domain.signup.entity.SignUpInfo
import io.reactivex.Completable
import io.reactivex.Single

interface SignUpRepository {
    fun getNoAccountStudentInfo(authCode : Int) : Single<NoAccountStudent>

    fun signUp(signUpInfo: SignUpInfo) : Completable

}