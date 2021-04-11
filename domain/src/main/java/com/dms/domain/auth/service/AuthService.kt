package com.dms.domain.auth.service

import com.dms.domain.auth.entity.LoggedInUser
import com.dms.domain.auth.request.LoginRequest
import com.dms.domain.auth.response.LoginResponse
import com.dms.domain.base.Result
import io.reactivex.Single

interface AuthService {
    fun login(loginRequestData: LoginRequest) : Single<Result<LoginResponse>>

    fun deleteLoginData() : Single<Result<Unit>>

    fun getLoginData() : Single<Result<LoggedInUser?>>

    fun saveLoginData(loggedInUser: LoggedInUser) : Single<Result<Unit>>
}