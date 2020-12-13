package com.dms.sms.di

import android.app.Application
import com.dms.sms.di.module.account.accountModule
import com.dms.sms.di.module.auth.authModule
import com.dms.sms.di.base.baseModule
import com.dms.sms.di.module.outing.outingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SMSApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SMSApplication)
            modules(
                listOf(baseModule, authModule, accountModule, networkModule, outingModule)
            )
        }
    }
}