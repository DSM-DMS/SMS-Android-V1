package com.dms.domain.outing.service

import com.dms.domain.base.Result
import com.dms.domain.outing.dto.OutingData
import com.dms.domain.outing.dto.OutingResponseData
import io.reactivex.Single

interface OutingService {
    fun createOuting(outingData: OutingData): Single<Result<OutingResponseData>>
}