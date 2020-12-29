package com.dms.domain.mypage.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.mypage.request.PasswordRequest
import com.dms.domain.mypage.service.MyPageService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class ChangePasswordUseCase(private val myPageService: MyPageService, disposable: CompositeDisposable):
    UseCase<PasswordRequest, Result<Unit>>(disposable) {
    override fun buildUseCase(data: PasswordRequest): Single<Result<Unit>> =
        myPageService.changePw(data)
}