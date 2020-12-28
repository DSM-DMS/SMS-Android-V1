package com.dms.domain.mypage.repository

import com.dms.domain.mypage.response.UserResponse
import io.reactivex.Single

interface MyPageRepository {
    fun getUserProfile(studentUUID: String): Single<UserResponse>

    fun getStudentUUID(): Single<String>
}