package com.dms.domain.auth.usecase

import com.dms.domain.auth.entity.LoggedInUser
import com.dms.domain.auth.service.AuthService
import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class SaveLoginDataUseCase(private val authService: AuthService, disposable: CompositeDisposable) : UseCase<LoggedInUser, Result<Unit>>(disposable) {
    override fun buildUseCase(data: LoggedInUser): Single<Result<Unit>> =
        authService.saveLoginData(data)
}