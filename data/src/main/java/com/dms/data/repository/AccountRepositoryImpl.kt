package com.dms.data.repository

import com.dms.data.datasource.AccountDataSource
import com.dms.data.dto.response.toEntity
import com.dms.domain.account.entity.Student
import com.dms.domain.account.repository.AccountRepository
import io.reactivex.Single

class AccountRepositoryImpl(private val accountDataSource: AccountDataSource) : AccountRepository {
    override fun getStudent(studentUUID: String): Single<Student> =
        accountDataSource.getStudent(studentUUID).map {
            it.toEntity()
        }
}