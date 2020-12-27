package com.dms.data.remote

import com.dms.data.dto.response.StudentResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {
    @GET("v1/students/uuid/{student_uuid}")
    fun getStudentInfo(@Path("student_uuid") studentUUID : String) : Single<StudentResponse>

    @GET("v1/students")
    fun getStudentsUUID(@Body studentUUIDs : List<String>) : Single<List<StudentResponse>>
}