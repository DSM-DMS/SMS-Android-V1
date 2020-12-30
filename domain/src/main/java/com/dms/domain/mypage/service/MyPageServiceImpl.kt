package com.dms.domain.mypage.service

import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.base.toSingleResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.mypage.repository.MyPageRepository
import com.dms.domain.mypage.request.PasswordRequest
import com.dms.domain.mypage.response.UserResponse
import io.reactivex.Completable
import io.reactivex.Single

class MyPageServiceImpl(
    private val myPageRepository: MyPageRepository,
    private val errorHandler: ErrorHandler
) : MyPageService {
    override fun getUserProfile(studentUUID: String): Single<Result<UserResponse>> =
        myPageRepository.getUserProfile(studentUUID).toResult(errorHandler)

    override fun getStudentUUID(): Single<Result<String>> =
        myPageRepository.getStudentUUID().toResult(errorHandler)

    override fun changePw(pw: PasswordRequest): Single<Result<Unit>> =
        myPageRepository.changePw(pw).toSingleResult(errorHandler)
}