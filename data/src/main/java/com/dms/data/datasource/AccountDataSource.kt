package com.dms.data.datasource

import com.dms.data.dto.response.StudentResponseData
import io.reactivex.Single

interface AccountDataSource {
    fun getStudent(studentUUID: String) : Single<StudentResponseData>

}