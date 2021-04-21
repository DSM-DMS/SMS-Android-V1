package com.dms.sms.di

import android.app.Application
import com.dms.sms.di.base.baseModule
import com.dms.sms.di.module.account.accountModule
import com.dms.sms.di.module.announcement.announcementModule
import com.dms.sms.di.module.auth.authModule
import com.dms.sms.di.module.mypage.myPageModule
import com.dms.sms.di.module.outing.outingModule
import com.dms.sms.di.module.schedule.scheduleModule
import com.dms.sms.di.module.signup.signUpModule
import com.dms.sms.di.module.timetable.timeTableModule
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.NullPointerException
import java.net.SocketException

class SMSApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler {
            if (it is UndeliverableException){

            }
            if (it is IOException ||it is SocketException)
              return@setErrorHandler
            if (it is InterruptedException)
                return@setErrorHandler
            if (it is NullPointerException || it is  IllegalArgumentException){
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), it)
                return@setErrorHandler

            }
            if (it is IllegalStateException){
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), it)
                return@setErrorHandler
            }
        }
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
                    scheduleModule,
                    timeTableModule,
                    signUpModule
                )
            )
        }
    }
}