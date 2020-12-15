package com.dms.data.datasource

import com.dms.data.dto.request.OutingRequest
import com.dms.data.dto.response.OutingResponse
import com.dms.data.remote.OutingApi
import io.reactivex.Single

class OutingDataSourceImpl(private val outingApi: OutingApi) : OutingDataSource {
    override fun createOuting(outingRequest: OutingRequest): Single<OutingResponse> =
        outingApi.createOuting(outingRequest)
}