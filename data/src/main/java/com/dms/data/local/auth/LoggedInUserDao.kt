package com.dms.data.local.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface LoggedInUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(loggedInUser: LoggedInUserData): Completable

    @Query("SELECT * FROM auto_login_user")
    fun getLoggedInUser(): Single<List<LoggedInUserData>>

    @Query("SELECT student_uuid FROM auto_login_user")
    fun getStudentUUID() : Single<List<String>>

    @Query("SELECT student_uuid FROM auto_login_user")
    fun getStdUUID() : String

    @Query("DELETE FROM auto_login_user")
    fun delete(): Completable
}

