package com.dms.data.local.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface LoggedInUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loggedInUser: LoggedInUserData) : Completable

    @Query("SELECT * FROM auto_login_user")
    fun getLoggedInUser() : Single<List<LoggedInUserData>>

    @Query("SELECT student_uuid FROM auto_login_user")
    fun getStudentUUID() : Single<List<String>>

    @Query("DELETE FROM auto_login_user")
    fun delete() : Completable
}

