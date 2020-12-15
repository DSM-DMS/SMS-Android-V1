package com.dms.data.datasource

import com.dms.data.dto.request.OutingRequest
import com.dms.data.dto.response.OutingResponse
import io.reactivex.Single

interface OutingDataSource {
    fun createOuting(outingRequest: OutingRequest) : Single<OutingResponse>
}