package com.dms.data.datasource

import com.dms.data.dto.request.OutingData
import com.dms.data.dto.response.OutingResponseData
import com.dms.data.remote.OutingApi
import io.reactivex.Single

class OutingDataSourceImpl(private val outingApi: OutingApi) : OutingDataSource {
    override fun createOuting(outingData: OutingData): Single<OutingResponseData> =
        outingApi.createOuting(outingData)
}