package com.dms.data.repository

import com.dms.data.datasource.SignUpDataSource
import com.dms.data.dto.request.toData
import com.dms.data.dto.response.toEntity
import com.dms.domain.signup.entity.NoAccountStudent
import com.dms.domain.signup.entity.SignUpInfo
import com.dms.domain.signup.repository.SignUpRepository
import io.reactivex.Completable
import io.reactivex.Single

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource) : SignUpRepository {
    override fun getNoAccountStudentInfo(authCode: Int): Single<NoAccountStudent> = signUpDataSource.getNoAccountStudentInfo(authCode).map { it.toEntity() }

    override fun signUp(signUpInfo: SignUpInfo): Completable =signUpDataSource.signUp(signUpInfo.toData())
}