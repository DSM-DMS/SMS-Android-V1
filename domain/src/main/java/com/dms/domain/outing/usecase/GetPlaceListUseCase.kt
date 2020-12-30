package com.dms.domain.outing.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.outing.response.SearchPlaceListResponse
import com.dms.domain.outing.service.OutingService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetPlaceListUseCase(private val outingService: OutingService,disposable: CompositeDisposable) :
    UseCase<String, Result<SearchPlaceListResponse>>(disposable){
    override fun buildUseCase(data: String): Single<Result<SearchPlaceListResponse>> =
        outingService.getPlaceList(data)
}