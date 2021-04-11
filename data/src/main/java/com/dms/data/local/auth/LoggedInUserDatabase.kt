package com.dms.data.local.auth

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoggedInUserData::class], version = 1,exportSchema = false)
abstract class LoggedInUserDatabase : RoomDatabase(){
    abstract fun loggedInUserDao() : LoggedInUserDao

    companion object{
        @Volatile
        private var instance : LoggedInUserDatabase? = null
        fun getInstance(context : Context) : LoggedInUserDatabase {
            return instance ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                LoggedInUserDatabase::class.java,
                "logged_in_user_db").build()
                Companion.instance = instance
                instance
            }
        }
    }
}