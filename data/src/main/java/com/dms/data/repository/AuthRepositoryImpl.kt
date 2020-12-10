package com.dms.data.repository

import com.dms.data.datasource.AuthDataSource
import com.dms.data.dto.request.toRequestData
import com.dms.data.dto.response.toDomainData
import com.dms.data.dto.response.toEntity
import com.dms.domain.auth.dto.LoginData
import com.dms.domain.auth.repository.AuthRepository
import com.dms.domain.account.entity.Student
import com.dms.domain.auth.dto.LoginResponseData
import io.reactivex.Single

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {
    override fun login(loginData: LoginData): Single<LoginResponseData> =
        authDataSource.login(loginData.toRequestData()).map {
            it.toDomainData()
        }





}