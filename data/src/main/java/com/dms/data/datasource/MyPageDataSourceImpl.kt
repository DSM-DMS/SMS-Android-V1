package com.dms.data.datasource

import com.dms.data.dto.response.UserResponseData
import com.dms.data.local.auth.LoggedInUserDatabase
import com.dms.data.remote.MyPageApi
import io.reactivex.Single

class MyPageDataSourceImpl(
    private val myPageApi: MyPageApi,
    private val autoLoginUserDatabase: LoggedInUserDatabase,
): MyPageDataSource {
    override fun getUserProfile(studentUUID: String): Single<UserResponseData> =
        myPageApi.getUserProfile(studentUUID)

    override fun getStudentUUID(): Single<String> =
        autoLoginUserDatabase.loggedInUserDao().getStudentUUID().map{
            it[0]
        }
}