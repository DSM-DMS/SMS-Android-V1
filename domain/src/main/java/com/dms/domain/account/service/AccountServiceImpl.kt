package com.dms.domain.account.service

import com.dms.domain.base.Result
import com.dms.domain.account.entity.Student
import com.dms.domain.account.repository.AccountRepository
import com.dms.domain.errorhandler.ErrorHandler
import com.dms.domain.base.toResult
import io.reactivex.Single

class AccountServiceImpl(private val accountRepository: AccountRepository, private val errorHandler: ErrorHandler) : AccountService {
    override fun getStudent(studentUUID: String): Single<Result<Student>> =
        accountRepository.getStudent(studentUUID).toResult(errorHandler)
}