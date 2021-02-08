package com.dms.domain.mypage.service

import com.dms.domain.base.Result
import com.dms.domain.mypage.request.PasswordRequest
import com.dms.domain.mypage.response.UserResponse
import io.reactivex.Completable
import io.reactivex.Single

interface MyPageService {
    fun getUserProfile(studentUUID: String): Single<Result<UserResponse>>

    fun getStudentUUID(): Single<Result<String>>

    fun changePw(pw: PasswordRequest): Single<Result<Unit>>
}