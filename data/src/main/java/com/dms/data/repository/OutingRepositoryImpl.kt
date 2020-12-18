package com.dms.data.repository

import com.dms.data.datasource.OutingDataSource
import com.dms.data.dto.request.toData
import com.dms.data.dto.response.toDomainData
import com.dms.domain.outing.repository.OutingRepository
import com.dms.domain.outing.request.OutingRequest
import com.dms.domain.outing.response.OutingResponse
import io.reactivex.Single

class OutingRepositoryImpl(private val outingDataSource: OutingDataSource) : OutingRepository {
    override fun createOuting(outingRequest: OutingRequest): Single<OutingResponse> =
        outingDataSource.createOuting(outingRequest.toData()).map {
            it.toDomainData()
        }

    override fun saveOutingUUID(uuid: String, content: String) =
        outingDataSource.saveOutingUUID(uuid, content)

    override fun getOutingUUID(content: String): String =
        outingDataSource.getOutingUUID(content)
}