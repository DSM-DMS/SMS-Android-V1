package com.dms.domain.auth.usecase

import com.dms.domain.base.UseCase
import com.dms.domain.auth.dto.LoginData
import com.dms.domain.auth.dto.LoginResponseData
import com.dms.domain.auth.service.AuthService
import com.dms.domain.base.Result
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class LoginUseCase(private val authService: AuthService,disposable: CompositeDisposable) : UseCase<LoginData, Result<LoginResponseData>>(disposable) {
    override fun buildUseCase(data: LoginData): Single<Result<LoginResponseData>> =
        authService.login(data)


}