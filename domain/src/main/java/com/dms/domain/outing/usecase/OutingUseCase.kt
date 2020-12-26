package com.dms.domain.outing.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.outing.request.OutingApplyRequest
import com.dms.domain.outing.service.OutingService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class OutingUseCase(private val outingService: OutingService, disposable: CompositeDisposable) :
    UseCase<OutingApplyRequest, Result<Unit>>(disposable) {
    override fun buildUseCase(data: OutingApplyRequest): Single<Result<Unit>> =
        outingService.createOuting(data)
}