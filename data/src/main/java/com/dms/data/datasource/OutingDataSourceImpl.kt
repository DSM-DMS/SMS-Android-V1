package com.dms.data.datasource

import com.dms.data.dto.request.OutingData
import com.dms.data.dto.response.DetailOutingResponseData
import com.dms.data.dto.response.OutingListResponseData
import com.dms.data.dto.response.OutingResponseData
import com.dms.data.dto.response.SearchPlaceListResponseData
import com.dms.data.local.auth.LoggedInUserDatabase
import com.dms.data.local.sharedpreference.SharedPreferencesStorage
import com.dms.data.remote.OutingApi
import io.reactivex.Single

class OutingDataSourceImpl(
    private val outingApi: OutingApi,
    private val autoLoginUserDatabase: LoggedInUserDatabase,
    private val pref: SharedPreferencesStorage
) : OutingDataSource {
    override fun createOuting(outingData: OutingData): Single<OutingResponseData> =
        outingApi.createOuting(outingData)

    override fun getOutingList(studentUUID: String): Single<OutingListResponseData> =
        outingApi.getOutingList(studentUUID)

    override fun getDetailOuting(outingUUID: String): Single<DetailOutingResponseData> =
        outingApi.getDetailOuting(outingUUID)

    override fun getPlaceList(keyword: String): Single<SearchPlaceListResponseData> =
        outingApi.getPlaceList(keyword)

    override fun saveOutingUUID(uuid: String, content: String) =
        pref.saveInfo(uuid, content)

    override fun getOutingUUID(content: String) =
        pref.getInfo(content)

    override fun getStudentUUID(): Single<String> =
        autoLoginUserDatabase.loggedInUserDao().getStudentUUID().map{
            it[0]
        }
}