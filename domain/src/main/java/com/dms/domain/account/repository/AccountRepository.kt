package com.dms.domain.account.repository

import com.dms.domain.account.entity.Student
import io.reactivex.Single

interface AccountRepository {
    fun getStudent(studentUUID : String) : Single<Student>

}