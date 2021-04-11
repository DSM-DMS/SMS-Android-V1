package com.dms.domain.auth.service

import com.dms.domain.auth.entity.LoggedInUser
import com.dms.domain.auth.repository.AuthRepository
import com.dms.domain.auth.request.LoginRequest
import com.dms.domain.auth.response.LoginResponse
import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.base.toSingleResult
import com.dms.domain.errorhandler.ErrorHandler
import io.reactivex.Single

class AuthServiceImpl(private val authRepository: AuthRepository, private val errorHandler: ErrorHandler) : AuthService{
    override fun login(loginRequestData: LoginRequest): Single<Result<LoginResponse>> =
        authRepository.login(loginRequestData).toResult(errorHandler)

    override fun deleteLoginData(): Single<Result<Unit>> =
        authRepository.deleteLoginData().toSingleResult(errorHandler)


    override fun getLoginData(): Single<Result<LoggedInUser?>> =
        authRepository.getLoginData().toResult(errorHandler)

    override fun saveLoginData(loggedInUser: LoggedInUser): Single<Result<Unit>> =
        authRepository.saveLoginData(loggedInUser).toSingleResult(errorHandler)
}