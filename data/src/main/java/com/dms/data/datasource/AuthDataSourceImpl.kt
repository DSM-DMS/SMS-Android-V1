package com.dms.data.datasource

import com.dms.data.remote.AuthApi
import com.dms.data.dto.request.LoginData
import com.dms.data.dto.response.LoginResponseData
import com.dms.data.local.db.LoggedInUserData
import com.dms.data.local.db.LoggedInUserDatabase
import io.reactivex.Completable
import io.reactivex.Single


class AuthDataSourceImpl(private val authApi: AuthApi, private val autoLoginUserDatabase: LoggedInUserDatabase) : AuthDataSource {
    override fun login(loginData: LoginData): Single<LoginResponseData> =
        authApi.login(loginData)

    override fun saveLoginData(loggedInUserData: LoggedInUserData): Completable =
        autoLoginUserDatabase.loggedInUserDao().insert(loggedInUserData)


    override fun getLoginData(): Single<LoggedInUserData> =
        autoLoginUserDatabase.loggedInUserDao().getLoggedInUser().map {
            it[0]
        }

    override fun deleteLoginData(): Completable =
        autoLoginUserDatabase.loggedInUserDao().delete()



}