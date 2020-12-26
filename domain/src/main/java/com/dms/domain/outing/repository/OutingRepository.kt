package com.dms.domain.outing.repository

import com.dms.domain.outing.request.OutingApplyRequest
import com.dms.domain.outing.response.OutingListResponse
import com.dms.domain.outing.response.OutingResponse
import io.reactivex.Single

interface OutingRepository {
    fun createOuting(outingApplyRequest: OutingApplyRequest): Single<OutingResponse>

    fun getOutingList(studentUUID: String): Single<OutingListResponse>

    fun saveOutingUUID(uuid: String, content: String)

    fun getOutingUUID(content: String): String

    fun getStudentUUID(content: String): String
}