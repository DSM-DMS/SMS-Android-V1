package com.dms.domain.signup.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.signup.entity.SignUpInfo
import com.dms.domain.signup.service.SignUpService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class SignUpUseCase(private val signUpService: SignUpService, disposable: CompositeDisposable) : UseCase<SignUpInfo,Result<Unit>>(disposable) {
    override fun buildUseCase(data: SignUpInfo): Single<Result<Unit>> =
        signUpService.signUp(data)
}