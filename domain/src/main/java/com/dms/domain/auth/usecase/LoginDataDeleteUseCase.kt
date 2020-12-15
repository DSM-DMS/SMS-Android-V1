package com.dms.domain.auth.usecase

import com.dms.domain.auth.service.AuthService
import com.dms.domain.base.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import com.dms.domain.base.Result

class LoginDataDeleteUseCase(private val authService: AuthService, disposable: CompositeDisposable) : UseCase<Unit, Result<Unit>>(disposable){
    override fun buildUseCase(data: Unit): Single<Result<Unit>> =
        authService.deleteLoginData()
}