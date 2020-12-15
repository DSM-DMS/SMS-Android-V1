package com.dms.domain.auth.usecase

import com.dms.domain.auth.entity.LoggedInUser
import com.dms.domain.auth.service.AuthService
import com.dms.domain.base.UseCase
import com.dms.domain.base.Result
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetLoginDataUseCase(private val authService: AuthService, disposable: CompositeDisposable) :
    UseCase<Unit, Result<LoggedInUser?>>(disposable) {
    override fun buildUseCase(data: Unit): Single<Result<LoggedInUser?>> =
        authService.getLoginData()
}