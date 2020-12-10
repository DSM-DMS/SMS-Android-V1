package com.dms.domain.auth.service

import com.dms.domain.base.Result
import com.dms.domain.auth.dto.LoginData
import com.dms.domain.auth.dto.LoginResponseData
import com.dms.domain.account.entity.Student
import com.dms.domain.auth.repository.AuthRepository
import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import io.reactivex.Single
import kotlin.math.log

class AuthServiceImpl(private val authRepository: AuthRepository, private val errorHandler: ErrorHandler) : AuthService{

    override fun login(loginData: LoginData): Single<Result<LoginResponseData>> =
        authRepository.login(loginData).toResult(errorHandler)

}