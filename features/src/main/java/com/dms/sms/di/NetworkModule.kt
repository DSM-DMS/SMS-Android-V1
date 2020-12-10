package com.dms.sms.di

import com.dms.data.remote.AccountApi
import com.dms.data.remote.AuthApi
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


    single<AccountApi> {
        Retrofit.Builder()
            .baseUrl(getProperty("base_url"))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AccountApi::class.java)
    }

    single<AuthApi> {
        Retrofit.Builder()
            .baseUrl(getProperty("base_url"))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }
}