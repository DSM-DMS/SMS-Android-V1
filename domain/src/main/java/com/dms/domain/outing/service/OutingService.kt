package com.dms.domain.outing.service

import com.dms.domain.base.Result
import com.dms.domain.outing.request.OutingRequest
import com.dms.domain.outing.response.OutingResponse
import io.reactivex.Single

interface OutingService {
    fun createOuting(outingRequest: OutingRequest): Single<Result<Unit>>
}