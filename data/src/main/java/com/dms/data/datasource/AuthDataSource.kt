package com.dms.data.datasource

import com.dms.data.dto.request.LoginRequest
import com.dms.data.dto.response.LoginResponse
import com.dms.data.dto.response.StudentResponse
import io.reactivex.Single

interface AuthDataSource {

    fun login(loginRequest: LoginRequest) : Single<LoginResponse>


}