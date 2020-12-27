package com.dms.domain.outing.service

import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.outing.repository.OutingRepository
import com.dms.domain.outing.request.OutingApplyRequest
import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.response.OutingListResponse
import io.reactivex.Single

class OutingServiceImpl(
    private val outingRepository: OutingRepository,
    private val errorHandler: ErrorHandler
) : OutingService {
    override fun createOuting(outingApplyRequest: OutingApplyRequest): Single<Result<Unit>> =
        outingRepository.createOuting(outingApplyRequest).map {
            outingRepository.saveOutingUUID(it.outingUUID, "outingUUID")
        }.toResult(errorHandler)

    override fun getOutingList(studentUUID: String): Single<Result<OutingListResponse>> =
        outingRepository.getOutingList(studentUUID).toResult(errorHandler)

    override fun getDetailOuting(outingUUID: String): Single<Result<DetailOutingResponse>> =
        outingRepository.getDetailOuting(outingUUID).toResult(errorHandler)

    override fun getStudentUUID(): Single<Result<String>> =
        outingRepository.getStudentUUID().toResult(errorHandler)

    override fun getOutingUUID(content: String): String =
        outingRepository.getOutingUUID(content)

}