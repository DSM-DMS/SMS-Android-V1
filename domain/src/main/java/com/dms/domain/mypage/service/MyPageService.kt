package com.dms.domain.mypage.service

import com.dms.domain.base.Result
import com.dms.domain.mypage.response.UserResponse
import io.reactivex.Single

interface MyPageService {
    fun getUserProfile(studentUUID: String): Single<Result<UserResponse>>

    fun getStudentUUID(): Single<Result<String>>
}