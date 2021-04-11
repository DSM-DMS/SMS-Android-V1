package com.dms.data.datasource

import com.dms.data.dto.request.LoginRequestData
import com.dms.data.dto.response.LoginResponseData
import com.dms.data.local.auth.LoggedInUserData
import com.dms.data.local.auth.LoggedInUserDatabase
import com.dms.data.local.sharedpreference.SharedPreferencesStorage
import com.dms.data.remote.AuthApi
import io.reactivex.Completable
import io.reactivex.Single


class AuthDataSourceImpl(
    private val authApi: AuthApi, private val autoLoginUserDatabase: LoggedInUserDatabase,
    private val sharedPreferencesStorage: SharedPreferencesStorage
) : AuthDataSource {
    override fun login(loginData: LoginRequestData): Single<LoginResponseData> =
        authApi.login(loginData)

    override fun saveLoginData(loggedInUserData: LoggedInUserData): Completable {
        sharedPreferencesStorage.saveInfo(loggedInUserData.token, "sms")
        return autoLoginUserDatabase.loggedInUserDao().insert(loggedInUserData)
    }

    override fun getLoginData(): Single<LoggedInUserData> =
        autoLoginUserDatabase.loggedInUserDao().getLoggedInUser().map {
            it[0]
        }

    override fun deleteLoginData(): Completable {
        sharedPreferencesStorage.clearToken("sms")
        return autoLoginUserDatabase.loggedInUserDao().delete()
    }


}