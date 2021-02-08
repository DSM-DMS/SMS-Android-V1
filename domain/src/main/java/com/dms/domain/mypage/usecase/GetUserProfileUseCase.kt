package com.dms.domain.mypage.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.mypage.response.UserResponse
import com.dms.domain.mypage.service.MyPageService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetUserProfileUseCase( private val myPageService: MyPageService, disposable: CompositeDisposable) :
    UseCase<String, Result<UserResponse>>(disposable) {
    override fun buildUseCase(data: String): Single<Result<UserResponse>> =
        myPageService.getUserProfile(data)
}