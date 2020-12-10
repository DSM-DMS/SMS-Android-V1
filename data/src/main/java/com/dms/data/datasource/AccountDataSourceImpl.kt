package com.dms.data.datasource

import com.dms.data.remote.AccountApi
import com.dms.data.dto.response.StudentResponse
import io.reactivex.Single

class AccountDataSourceImpl(private val accountApi: AccountApi) : AccountDataSource {
    override fun getStudent(studentUUID: String): Single<StudentResponse> =
        accountApi.getStudentInfo(studentUUID)
}