package com.dms.data.base

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1dWlkIjoic3R1ZGVudC03MjA3MTk0MDU1MTIiLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiZXhwIjoxNjA4MjAxMTQ0fQ.IPiupCnvg58ZdalnXD2YbmIDjdJ9mv-Mpa6ynmLSF2sgLo70xbqqq_Lb2XxAqvpTn8ABGdF-YXg6wM_3puWNdg")
            .build()

        return chain.proceed(request)
    }
}