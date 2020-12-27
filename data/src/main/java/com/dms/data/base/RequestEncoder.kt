package com.dms.data.base

import com.dms.data.BuildConfig
import com.github.mervick.aes_everywhere.Aes256

object RequestEncoder {
    fun getRequestSecurityKey() : String{
        val requestKey = BuildConfig.SECURITY_BASE_PLAIN + ":" + System.currentTimeMillis()/1000
        return Aes256.encrypt(requestKey, BuildConfig.SECURITY_PASS_PHRASE)
    }
}