package com.dms.domain.outing.repository

import com.dms.domain.outing.request.OutingRequest
import com.dms.domain.outing.response.OutingResponse
import io.reactivex.Single

interface OutingRepository {
    fun createOuting(outingRequest: OutingRequest): Single<OutingResponse>

    fun saveOutingUUID(uuid: String, content: String)

    fun getOutingUUID(content: String): String
}