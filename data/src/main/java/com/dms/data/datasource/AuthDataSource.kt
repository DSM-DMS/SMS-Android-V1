package com.dms.data.datasource

import com.dms.data.dto.request.LoginRequestData
import com.dms.data.dto.response.LoginResponseData
import com.dms.data.local.db.LoggedInUserData
import io.reactivex.Completable
import io.reactivex.Single

interface AuthDataSource {

    fun login(loginData: LoginRequestData) : Single<LoginResponseData>

    fun saveLoginData(loggedInUserData: LoggedInUserData) : Completable

    fun getLoginData() : Single<LoggedInUserData>

    fun deleteLoginData() : Completable


}