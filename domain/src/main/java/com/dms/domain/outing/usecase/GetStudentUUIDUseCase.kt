package com.dms.domain.outing.usecase

import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import com.dms.domain.outing.service.OutingService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetStudentUUIDUseCase(private val outingService: OutingService, disposable: CompositeDisposable) :
    UseCase<Unit, Result<String>>(disposable) {
    override fun buildUseCase(data: Unit): Single<Result<String>> =
        outingService.getStudentUUID()
}