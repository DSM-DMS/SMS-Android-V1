package com.dms.domain.account.service

import com.dms.domain.base.Result
import com.dms.domain.account.entity.Student
import io.reactivex.Single

interface AccountService {
    fun getStudent(studentUUID : String) : Single<Result<Student>>

}