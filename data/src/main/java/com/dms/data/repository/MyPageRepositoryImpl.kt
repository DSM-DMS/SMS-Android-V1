package com.dms.data.repository

import com.dms.data.datasource.MyPageDataSource
import com.dms.data.dto.response.toEntity
import com.dms.domain.mypage.repository.MyPageRepository
import com.dms.domain.mypage.response.UserResponse
import io.reactivex.Single

class MyPageRepositoryImpl(private val myPageDataSource: MyPageDataSource) : MyPageRepository {
    override fun getUserProfile(studentUUID: String): Single<UserResponse> =
        myPageDataSource.getUserProfile(studentUUID).map { it.toEntity() }

    override fun getStudentUUID(): Single<String> =
        myPageDataSource.getStudentUUID()
}