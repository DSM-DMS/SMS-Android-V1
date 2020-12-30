package com.dms.domain.outing.repository

import com.dms.domain.outing.request.OutingApplyRequest
import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.response.OutingListResponse
import com.dms.domain.outing.response.OutingResponse
import com.dms.domain.outing.response.SearchPlaceListResponse
import io.reactivex.Single

interface OutingRepository {
    fun createOuting(outingApplyRequest: OutingApplyRequest): Single<OutingResponse>

    fun getOutingList(studentUUID: String): Single<OutingListResponse>

    fun getDetailOuting(outingUUID: String): Single<DetailOutingResponse>

    fun getPlaceList(keyword: String): Single<SearchPlaceListResponse>

    fun saveOutingUUID(uuid: String, content: String)

    fun getOutingUUID(content: String): String

    fun getStudentUUID(): Single<String>
}