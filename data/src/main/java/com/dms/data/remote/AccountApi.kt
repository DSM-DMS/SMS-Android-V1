package com.dms.data.remote

import com.dms.data.dto.response.StudentResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {
    @GET("v1/students/uuid/{student_uuid}")
    fun getStudentInfo(@Path("student_uuid") studentUUID : String) : Single<StudentResponseData>
}