package com.dms.domain.signup.service

import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.signup.entity.NoAccountStudent
import com.dms.domain.signup.entity.SignUpInfo
import com.dms.domain.signup.repository.SignUpRepository
import io.reactivex.Single
import com.dms.domain.base.Result
import com.dms.domain.base.toSingleResult

class SignUpServiceImpl(
    private val signUpRepository: SignUpRepository, private val errorHandler: ErrorHandler
) : SignUpService {
    override fun getNoAccountStudentInfo(authCode: Int): Single<Result<NoAccountStudent>> =
        signUpRepository.getNoAccountStudentInfo(authCode).toResult(errorHandler)

    override fun signUp(signUpInfo: SignUpInfo): Single<Result<Unit>> =
        signUpRepository.signUp(signUpInfo).toSingleResult(errorHandler)
}