package com.dms.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [AutoLoginUser::class], version = 0)
abstract class AutoLoginUserDatabase : RoomDatabase(){
    abstract fun autoLoginUserDao() : AutoLoginUserDao

    companion object{
        @Volatile
        private var instance : AutoLoginUserDatabase? = null
        fun getInstance(context : Context) : AutoLoginUserDatabase{
            return this.instance ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                AutoLoginUserDatabase::class.java,
                "auto_login_user_db").build()
                this.instance = instance
                instance
            }
        }

    }

}