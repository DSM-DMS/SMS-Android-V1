package com.dms.sms.di

import android.app.Application
import com.dms.sms.di.module.outing.outingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    outingModule
                )
            )
        }
    }
}