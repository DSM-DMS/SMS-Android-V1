package com.dms.domain.outing.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.outing.dto.OutingData
import com.dms.domain.outing.dto.OutingResponseData
import com.dms.domain.outing.service.OutingService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class OutingUseCase(private val outingService: OutingService, disposable: CompositeDisposable) :
    UseCase<OutingData, Result<OutingResponseData>>(disposable) {
    override fun buildUseCase(data: OutingData): Single<Result<OutingResponseData>> =
        outingService.createOuting(data)
}