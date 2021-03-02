package com.dms.domain.signup.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.signup.entity.NoAccountStudent
import com.dms.domain.signup.service.SignUpService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetNoAccountStudentInfoUseCase(private val signUpService: SignUpService, disposable: CompositeDisposable) : UseCase<Int,Result<NoAccountStudent>>(disposable) {
    override fun buildUseCase(data: Int): Single<Result<NoAccountStudent>> =
        signUpService.getNoAccountStudentInfo(data)
}