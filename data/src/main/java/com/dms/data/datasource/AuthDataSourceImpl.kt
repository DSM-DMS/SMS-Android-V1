package com.dms.data.datasource

import com.dms.data.remote.AuthApi
import com.dms.data.dto.request.LoginRequest
import com.dms.data.dto.response.LoginResponse
import io.reactivex.Single

class AuthDataSourceImpl(private val authApi: AuthApi) : AuthDataSource {
    override fun login(loginRequest: LoginRequest): Single<LoginResponse> =
        authApi.login(loginRequest)

}