package com.dms.data.base

import android.util.Log
import com.dms.data.local.db.LoggedInUserDatabase
import com.dms.data.local.sharedpreference.SharedPreferencesStorage
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val preference : SharedPreferencesStorage) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization",preference.getToken())
            .build()

        return chain.proceed(request)
    }


}