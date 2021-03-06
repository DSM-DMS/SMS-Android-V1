package com.dms.sms.di

import com.dms.data.base.AuthorizationInterceptor
import com.dms.sms.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
            .baseUrl("https://api.dsm-sms.com")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(get<AuthorizationInterceptor>())
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
