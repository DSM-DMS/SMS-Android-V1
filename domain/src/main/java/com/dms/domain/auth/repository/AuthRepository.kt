package com.dms.domain.auth.repository

import com.dms.domain.auth.dto.LoginData
import com.dms.domain.account.entity.Student
import com.dms.domain.auth.dto.LoginResponseData
import io.reactivex.Single

interface AuthRepository {
    fun login(loginData : LoginData) : Single<LoginResponseData>

}