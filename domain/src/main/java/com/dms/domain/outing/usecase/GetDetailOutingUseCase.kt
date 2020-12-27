package com.dms.domain.outing.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.service.OutingService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetDetailOutingUseCase(private val outingService: OutingService, disposable: CompositeDisposable) :
    UseCase<String, Result<DetailOutingResponse>>(disposable){
    override fun buildUseCase(data: String): Single<Result<DetailOutingResponse>> =
        outingService.getDetailOuting(data)
}