package com.dms.data.base

import com.dms.data.local.sharedpreference.SharedPreferencesStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val preference : SharedPreferencesStorage) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization",preference.getInfo("sms"))
            .build()

        return chain.proceed(request)
    }
}