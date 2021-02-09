package com.dms.sms.di.module

import com.dms.data.remote.*
import retrofit2.Retrofit

fun provideMyPageApi(retrofit: Retrofit): MyPageApi =
    retrofit.create(MyPageApi::class.java)

fun provideOutingApi(retrofit: Retrofit): OutingApi =
    retrofit.create(OutingApi::class.java)

fun provideAuthApi(retrofit: Retrofit): AuthApi =
    retrofit.create(AuthApi::class.java)

fun provideAnnouncementApi(retrofit: Retrofit): AnnouncementApi =
    retrofit.create(AnnouncementApi::class.java)

fun provideScheduleApi(retrofit: Retrofit) : ScheduleApi =
    retrofit.create(ScheduleApi::class.java)

fun provideAccountApi(retrofit: Retrofit): AccountApi =
    retrofit.create(AccountApi::class.java)

fun provideTimeTableApi(retrofit: Retrofit): TimeTableApi =
    retrofit.create(TimeTableApi::class.java)
