package com.dms.domain.outing.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.outing.response.OutingListResponse
import com.dms.domain.outing.service.OutingService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetOutingListUseCase(private val outingService: OutingService, disposable: CompositeDisposable) :
    UseCase<String, Result<OutingListResponse>>(disposable){
    override fun buildUseCase(data: String): Single<Result<OutingListResponse>> =
        outingService.getOutingList(data)
}