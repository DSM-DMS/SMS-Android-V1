package com.dms.data.datasource

import com.dms.data.dto.request.OutingData
import com.dms.data.dto.response.OutingListResponseData
import com.dms.data.dto.response.OutingResponseData
import io.reactivex.Single

interface OutingDataSource {
    fun createOuting(outingData: OutingData): Single<OutingResponseData>

    fun getOutingList(studentUUID: String): Single<OutingListResponseData>

    fun saveOutingUUID(uuid: String, content: String)

    fun getOutingUUID(content: String): String

    fun getStudentUUID(content: String): String
}