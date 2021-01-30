package com.dms.sms.feature.schedule

import android.util.Log
import com.dms.sms.feature.schedule.model.ScheduleDateModel
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate() : ScheduleDateModel {
    val date = Date(System.currentTimeMillis())
    val formatter = SimpleDateFormat("yyyy.MM", Locale.KOREA).format(date)
    val yearAndMonth = formatter.split(".")
    Log.d("ㅇㅇㅇㅇㅇ",ScheduleDateModel(yearAndMonth[0].toInt(), yearAndMonth[1].toInt()).toString())
    return ScheduleDateModel(yearAndMonth[0].toInt(), yearAndMonth[1].toInt())
}