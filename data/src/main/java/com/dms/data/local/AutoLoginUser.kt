package com.dms.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auto_login_user")
data class AutoLoginUser(@PrimaryKey val student_uuid : String, @PrimaryKey val token: String, @PrimaryKey val isAutoLoginChecked: Boolean)