package com.dms.data.local.sharedpreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesStorage(private val context: Context) {
    private var pref : SharedPreferences? = null

    fun getToken() : String{
        if (pref == null) pref = context.getSharedPreferences("sms", MODE_PRIVATE)
        return  "Bearer " + pref?.getString("token", "")
    }

    fun saveToken(token: String) {
        if (pref == null) pref = context.getSharedPreferences("sms", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun clearToken(){
        if(pref == null) pref = context.getSharedPreferences("sms", MODE_PRIVATE)
        val editor:SharedPreferences.Editor = pref!!.edit()
        editor.clear()
        editor.apply()
    }
}