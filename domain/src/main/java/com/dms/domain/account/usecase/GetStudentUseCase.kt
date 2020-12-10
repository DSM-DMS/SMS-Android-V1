package com.dms.domain.account.usecase

import com.dms.domain.base.UseCase
import com.dms.domain.base.Result
import com.dms.domain.account.entity.Student
import com.dms.domain.account.service.AccountService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetStudentUseCase(
    private val accountService: AccountService,
    disposable: CompositeDisposable
) : UseCase<String, Result<Student>>(disposable) {
    override fun buildUseCase(data: String): Single<Result<Student>> =
        accountService.getStudent(data)
}