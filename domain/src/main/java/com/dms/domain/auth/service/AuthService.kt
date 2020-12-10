package com.dms.domain.auth.service

import com.dms.domain.base.Result
import com.dms.domain.auth.dto.LoginData
import com.dms.domain.auth.dto.LoginResponseData
import io.reactivex.Single

interface AuthService {
    fun login(loginData: LoginData) : Single<Result<LoginResponseData>>
}