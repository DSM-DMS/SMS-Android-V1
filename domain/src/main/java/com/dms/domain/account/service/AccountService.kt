package com.dms.domain.account.service

import com.dms.domain.account.entity.Student
import com.dms.domain.base.Result
import io.reactivex.Single

interface AccountService {
    fun getStudent(studentUUID : String) : Single<Result<Student>>
}