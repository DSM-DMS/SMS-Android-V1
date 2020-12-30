package com.dms.domain.mypage.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.mypage.service.MyPageService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetStudentUUIDUseCase ( private val myPageService: MyPageService, disposable: CompositeDisposable) :
    UseCase<Unit, Result<String>>(disposable) {
    override fun buildUseCase(data: Unit): Single<Result<String>> =
        myPageService.getStudentUUID()
}