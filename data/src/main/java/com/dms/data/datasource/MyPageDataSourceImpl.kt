package com.dms.data.datasource

import com.dms.data.dto.request.PasswordRequestData
import com.dms.data.dto.response.UserResponseData
import com.dms.data.local.auth.LoggedInUserDatabase
import com.dms.data.remote.MyPageApi
import io.reactivex.Completable
import io.reactivex.Single

class MyPageDataSourceImpl(
    private val myPageApi: MyPageApi,
    private val autoLoginUserDatabase: LoggedInUserDatabase,
) : MyPageDataSource {
    override fun getUserProfile(studentUUID: String): Single<UserResponseData> =
        myPageApi.getUserProfile(studentUUID)

    override fun getStudentUUID(): Single<String> =
        autoLoginUserDatabase.loggedInUserDao().getStudentUUID().map {
            it[0]
        }

    override fun getStdUUID(): String =
        autoLoginUserDatabase.loggedInUserDao().getStdUUID()

    override fun changePw(pw: PasswordRequestData): Completable {
        var stdUUID = ""

        val thread = Thread {
            stdUUID = getStdUUID()
        }
        thread.start()
        thread.join()

        return myPageApi.changePw(stdUUID, pw)
    }
}