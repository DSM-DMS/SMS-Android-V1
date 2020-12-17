package com.dms.sms.di

import com.dms.data.base.AuthorizationInterceptor
import com.dms.data.remote.AccountApi
import com.dms.data.remote.AnnouncementApi
import com.dms.data.remote.Api
import com.dms.data.remote.AuthApi
import com.dms.data.remote.OutingApi
import com.dms.sms.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        AuthorizationInterceptor(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(get<AuthorizationInterceptor>())
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<OutingApi> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor())
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OutingApi::class.java)
    }


}
