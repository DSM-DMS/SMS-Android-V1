package com.dms.data.repository

import com.dms.data.datasource.AuthDataSource
import com.dms.data.dto.request.toData
import com.dms.data.dto.response.toDomainData
import com.dms.data.local.auth.toData
import com.dms.data.local.auth.toEntity
import com.dms.domain.auth.request.LoginRequest
import com.dms.domain.auth.repository.AuthRepository
import com.dms.domain.auth.response.LoginResponse
import com.dms.domain.auth.entity.LoggedInUser
import io.reactivex.Completable
import io.reactivex.Single

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {
    override fun login(loginRequestData: LoginRequest): Single<LoginResponse> =
        authDataSource.login(loginRequestData.toData()).map {
            it.toDomainData()
        }

    override fun saveLoginData(loggedInUser: LoggedInUser): Completable =
        authDataSource.saveLoginData(loggedInUser.toData())


    override fun deleteLoginData(): Completable =
        authDataSource.deleteLoginData()


    override fun getLoginData(): Single<LoggedInUser?> =
        authDataSource.getLoginData().map {
            it.toEntity()
        }


}