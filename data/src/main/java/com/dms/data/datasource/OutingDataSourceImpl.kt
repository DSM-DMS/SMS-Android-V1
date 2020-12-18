package com.dms.data.datasource

import com.dms.data.dto.request.OutingData
import com.dms.data.dto.response.OutingListResponseData
import com.dms.data.dto.response.OutingResponseData
import com.dms.data.local.sharedpreference.SharedPreferencesStorage
import com.dms.data.remote.OutingApi
import io.reactivex.Single

class OutingDataSourceImpl(
    private val outingApi: OutingApi,
    private val pref: SharedPreferencesStorage
) : OutingDataSource {
    override fun createOuting(outingData: OutingData): Single<OutingResponseData> =
        outingApi.createOuting(outingData)

    override fun getOutingList(studentUUID: String): Single<ArrayList<OutingListResponseData>> =
        outingApi.getOutingList(studentUUID)

    override fun saveOutingUUID(uuid: String, content: String) =
        pref.saveInfo(uuid, "outingUUID")

    override fun getOutingUUID(content: String) =
        pref.getInfo("outingUUID")

}