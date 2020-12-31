package com.dms.data.datasource

import com.dms.data.dto.request.PasswordRequestData
import com.dms.data.dto.response.UserResponseData
import io.reactivex.Completable
import io.reactivex.Single

interface MyPageDataSource {
    fun getUserProfile(studentUUID: String): Single<UserResponseData>

    fun getStudentUUID(): Single<String>

    fun getStdUUID(): String

    fun changePw(pw: PasswordRequestData): Completable
}