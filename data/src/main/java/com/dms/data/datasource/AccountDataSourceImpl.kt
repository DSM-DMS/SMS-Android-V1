package com.dms.data.datasource

import com.dms.data.dto.response.StudentResponseData
import com.dms.data.remote.AccountApi
import io.reactivex.Single

class AccountDataSourceImpl(private val accountApi: AccountApi) : AccountDataSource {
    override fun getStudent(studentUUID: String): Single<StudentResponseData> =
        accountApi.getStudentInfo(studentUUID)
}