package com.dms.data.repository

import com.dms.data.datasource.OutingDataSource
import com.dms.data.dto.request.toData
import com.dms.data.dto.response.toDomainData
import com.dms.domain.outing.repository.OutingRepository
import com.dms.domain.outing.request.AccessOutingRequest
import com.dms.domain.outing.request.OutingApplyRequest
import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.response.OutingListResponse
import com.dms.domain.outing.response.OutingResponse
import com.dms.domain.outing.response.SearchPlaceListResponse
import io.reactivex.Completable
import io.reactivex.Single

class OutingRepositoryImpl(private val outingDataSource: OutingDataSource) : OutingRepository {
    override fun createOuting(outingApplyRequest: OutingApplyRequest): Single<OutingResponse> =
        outingDataSource.createOuting(outingApplyRequest.toData()).map {
            it.toDomainData()
        }

    override fun getOutingList(studentUUID: String): Single<OutingListResponse> =
        outingDataSource.getOutingList(studentUUID).map {
            it.toDomainData()
        }

    override fun getDetailOuting(outingUUID: String): Single<DetailOutingResponse> =
        outingDataSource.getDetailOuting(outingUUID).map {
            it.toDomainData()
        }

    override fun getPlaceList(keyword: String): Single<SearchPlaceListResponse> =
        outingDataSource.getPlaceList(keyword).map{
            it.toDomainData()
        }

    override fun postOutingAction(actionData: AccessOutingRequest): Completable =
        outingDataSource.postOutingAction(actionData.toData())

    override fun saveOutingUUID(uuid: String, content: String) =
        outingDataSource.saveOutingUUID(uuid, content)

    override fun getOutingUUID(content: String): String =
        outingDataSource.getOutingUUID(content)

    override fun getStudentUUID(): String =
        outingDataSource.getStudentUUID()
}