package com.dms.data.datasource

import com.dms.data.dto.response.StudentResponse
import io.reactivex.Single

interface AccountDataSource {
    fun getStudent(studentUUID: String) : Single<StudentResponse>

}