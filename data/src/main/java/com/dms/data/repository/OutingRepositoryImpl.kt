package com.dms.data.repository

import com.dms.data.datasource.OutingDataSource
import com.dms.data.dto.request.toRequestData
import com.dms.data.dto.response.toDomainData
import com.dms.domain.outing.dto.OutingData
import com.dms.domain.outing.dto.OutingResponseData
import com.dms.domain.outing.repository.OutingRepository
import io.reactivex.Single

class OutingRepositoryImpl(private val outingDataSource: OutingDataSource) : OutingRepository {
    override fun createOuting(outingData: OutingData): Single<OutingResponseData> =
        outingDataSource.createOuting(outingData.toRequestData()).map {
            it.toDomainData()
        }
}