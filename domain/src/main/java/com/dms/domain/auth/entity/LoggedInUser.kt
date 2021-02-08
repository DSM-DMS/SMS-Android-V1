package com.dms.domain.auth.entity

data class LoggedInUser(val accessToken: String,
                        val studentUUID: String,
                        val isAutoLoginChecked : Boolean)
