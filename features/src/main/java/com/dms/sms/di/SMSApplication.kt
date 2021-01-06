package com.dms.sms.di

import android.app.Application
import com.dms.sms.di.base.baseModule
import com.dms.sms.di.module.account.accountModule
import com.dms.sms.di.module.announcement.announcementModule
import com.dms.sms.di.module.auth.authModule
import com.dms.sms.di.module.mypage.myPageModule
import com.dms.sms.di.module.outing.outingModule
import com.dms.sms.di.module.timetable.timeTableModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SMSApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SMSApplication)
            modules(
                listOf(
                    baseModule,
                    authModule,
                    accountModule,
                    networkModule,
                    outingModule,
                    announcementModule,
                    myPageModule,
                    timeTableModule
                )
            )
        }
    }
}