package com.dms.domain.auth.repository

import com.dms.domain.auth.request.LoginRequest
import com.dms.domain.auth.response.LoginResponse
import com.dms.domain.auth.entity.LoggedInUser
import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {
    fun login(loginRequestData : LoginRequest) : Single<LoginResponse>

    fun saveLoginData(loggedInUser: LoggedInUser) : Completable

    fun deleteLoginData() : Completable

    fun getLoginData() : Single<LoggedInUser?>

}