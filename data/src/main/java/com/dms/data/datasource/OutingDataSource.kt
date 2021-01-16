package com.dms.data.datasource

import com.dms.data.dto.request.OutingData
import com.dms.data.dto.response.DetailOutingResponseData
import com.dms.data.dto.response.OutingListResponseData
import com.dms.data.dto.response.OutingResponseData
import com.dms.data.dto.response.SearchPlaceListResponseData
import io.reactivex.Completable
import io.reactivex.Single

interface OutingDataSource {
    fun createOuting(outingData: OutingData): Single<OutingResponseData>

    fun getOutingList(studentUUID: String): Single<OutingListResponseData>

    fun getDetailOuting(outingUUID: String): Single<DetailOutingResponseData>

    fun getPlaceList(keyword: String): Single<SearchPlaceListResponseData>

    fun postOutingAction(action: String): Completable

    fun saveOutingUUID(uuid: String, content: String)

    fun getOutingUUID(content: String): String

    fun getStudentUUID(): String
}