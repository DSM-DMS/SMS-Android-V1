package com.dms.domain.outing.service

import com.dms.domain.base.Result
import com.dms.domain.outing.request.OutingApplyRequest
import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.response.OutingListResponse
import io.reactivex.Single

interface OutingService {
    fun createOuting(outingApplyRequest: OutingApplyRequest): Single<Result<Unit>>

    fun getOutingList(studentUUID: String): Single<Result<OutingListResponse>>

    fun getDetailOuting(outingUUID: String): Single<Result<DetailOutingResponse>>

    fun getStudentUUID(): Single<Result<String>>

    fun getOutingUUID(content: String): String
}