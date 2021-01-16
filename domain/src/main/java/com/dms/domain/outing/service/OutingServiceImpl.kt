package com.dms.domain.outing.service

import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.base.toSingleResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.outing.repository.OutingRepository
import com.dms.domain.outing.request.OutingApplyRequest
import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.response.OutingListResponse
import com.dms.domain.outing.response.SearchPlaceListResponse
import com.dms.domain.util.getDate
import io.reactivex.Single

class OutingServiceImpl(
    private val outingRepository: OutingRepository,
    private val errorHandler: ErrorHandler
) : OutingService {
    override fun createOuting(outingApplyRequest: OutingApplyRequest): Single<Result<Unit>> =
        outingRepository.createOuting(outingApplyRequest).map {
            outingRepository.saveOutingUUID(
                it.outingUUID,
                getDate(outingApplyRequest.startTime.toLong() * 1000)
            )
        }.toResult(errorHandler)


    override fun getOutingList(studentUUID: String): Single<Result<OutingListResponse>> =
        outingRepository.getOutingList(studentUUID).toResult(errorHandler)

    override fun getDetailOuting(outingUUID: String): Single<Result<DetailOutingResponse>> =
        outingRepository.getDetailOuting(outingUUID).toResult(errorHandler)

    override fun getPlaceList(keyword: String): Single<Result<SearchPlaceListResponse>> =
        outingRepository.getPlaceList(keyword).toResult(errorHandler)

    override fun postOutingAction(action: String): Single<Result<Unit>> =
        outingRepository.postOutingAction(action).toSingleResult(errorHandler)

    override fun getStudentUUID(): String =
        outingRepository.getStudentUUID()

    override fun getOutingUUID(content: String): String =
        outingRepository.getOutingUUID(content)

}