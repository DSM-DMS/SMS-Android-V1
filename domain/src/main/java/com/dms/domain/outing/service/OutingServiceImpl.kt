package com.dms.domain.outing.service

import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.outing.repository.OutingRepository
import com.dms.domain.outing.request.OutingRequest
import com.dms.domain.outing.response.OutingResponse
import io.reactivex.Single

class OutingServiceImpl(private val outingRepository: OutingRepository, private val errorHandler: ErrorHandler): OutingService {
    override fun createOuting(outingRequest: OutingRequest): Single<Result<OutingResponse>> =
        outingRepository.createOuting(outingRequest).toResult(errorHandler)
}