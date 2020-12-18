package com.dms.domain.outing.service

import android.util.Log
import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.outing.repository.OutingRepository
import com.dms.domain.outing.request.OutingRequest
import com.dms.domain.outing.response.OutingResponse
import io.reactivex.Single

class OutingServiceImpl(private val outingRepository: OutingRepository, private val errorHandler: ErrorHandler): OutingService {
    override fun createOuting(outingRequest: OutingRequest): Single<Result<Unit>> {
        Log.d("outingUUID", outingRepository.getOutingUUID("outingUUID"))

        return outingRepository.createOuting(outingRequest).map {
            outingRepository.saveOutingUUID(it.outingUUID, "outingUUID")
        }.toResult(errorHandler)

    }
}