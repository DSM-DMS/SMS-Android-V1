package com.dms.domain.outing.repository

import com.dms.domain.outing.dto.OutingData
import com.dms.domain.outing.dto.OutingResponseData
import io.reactivex.Single

interface OutingRepository {
    fun createOuting(outingData: OutingData): Single<OutingResponseData>
}