package com.dms.domain.auth.usecase

import com.dms.domain.base.UseCase
import com.dms.domain.auth.request.LoginRequest
import com.dms.domain.auth.response.LoginResponse
import com.dms.domain.auth.service.AuthService
import com.dms.domain.base.Result
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class LoginUseCase(private val authService: AuthService,disposable: CompositeDisposable) : UseCase<LoginRequest, Result<LoginResponse>>(disposable) {
    override fun buildUseCase(data: LoginRequest): Single<Result<LoginResponse>> =
        authService.login(data)


}