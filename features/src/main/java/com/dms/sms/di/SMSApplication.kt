package com.dms.sms.di

import android.app.Application
import com.dms.sms.di.account.accountModule
import com.dms.sms.di.auth.authModule
import com.dms.sms.di.base.baseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SMSApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SMSApplication)
            modules(
                listOf(baseModule, authModule, accountModule, networkModule)
            )
        }
    }
}