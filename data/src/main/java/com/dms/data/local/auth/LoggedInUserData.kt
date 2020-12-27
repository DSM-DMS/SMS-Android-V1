package com.dms.data.local.auth

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dms.domain.auth.entity.LoggedInUser

@Entity(tableName = "auto_login_user")
data class LoggedInUserData(@PrimaryKey val student_uuid : String, @ColumnInfo val token: String, @ColumnInfo val isAutoLoginChecked: Boolean)

fun LoggedInUserData.toEntity() : LoggedInUser =
    LoggedInUser(this.token, this.student_uuid, this.isAutoLoginChecked)

fun LoggedInUser.toData() : LoggedInUserData =
    LoggedInUserData(this.accessToken, this.studentUUID, this.isAutoLoginChecked)