package com.dms.domain.outing.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.outing.request.AccessOutingRequest
import com.dms.domain.outing.service.OutingService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class PostOutingActionUseCase(private val outingService: OutingService, disposable: CompositeDisposable) :
    UseCase<AccessOutingRequest, Result<Unit>>(disposable){
    override fun buildUseCase(data: AccessOutingRequest): Single<Result<Unit>> =
        outingService.postOutingAction(data)
}