package com.dms.data.datasource

import com.dms.data.dto.request.OutingData
import com.dms.data.dto.response.OutingResponseData
import io.reactivex.Single

interface OutingDataSource {
    fun createOuting(outingData: OutingData) : Single<OutingResponseData>
}