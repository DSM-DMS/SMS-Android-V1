package com.dms.domain.outing.service

import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.outing.dto.OutingData
import com.dms.domain.outing.dto.OutingResponseData
import com.dms.domain.outing.repository.OutingRepository
import io.reactivex.Single

class OutingServiceImpl(private val outingRepository: OutingRepository, private val errorHandler: ErrorHandler): OutingService {
    override fun createOuting(outingData: OutingData): Single<Result<OutingResponseData>> =
        outingRepository.createOuting(outingData).toResult(errorHandler)
}