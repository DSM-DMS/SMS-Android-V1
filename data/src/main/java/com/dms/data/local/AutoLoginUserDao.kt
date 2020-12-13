package com.dms.data.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AutoLoginUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(autoLoginUser: AutoLoginUser) : Completable

    @Query("SELECT * FROM auto_login_user")
    fun getAutoLoginUser() : Single<List<AutoLoginUser>>

    @Query("DELETE FROM auto_login_user")
    fun delete() : Completable
}

