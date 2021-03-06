package com.dms.data.local.sharedpreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log

class SharedPreferencesStorage(private val context: Context) {
    private var pref: SharedPreferences? = null

    fun getInfo(content: String?): String {
        if (pref == null) pref = context.getSharedPreferences(content, MODE_PRIVATE)
        return if (content == "sms") {
            "Bearer " + pref?.getString(content,"")
        } else
            pref?.getString(content, "").toString()

    }

    fun saveInfo(info: String, content: String) {
        if (pref == null) pref = context.getSharedPreferences(content, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString(content, info)
        editor.apply()
    }

    fun clearToken(content: String) {
        if (pref == null) pref = context.getSharedPreferences(content, MODE_PRIVATE)
        pref!!.edit().remove(content).apply()
    }
}