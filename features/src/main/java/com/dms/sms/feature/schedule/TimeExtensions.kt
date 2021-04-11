package com.dms.sms.feature.schedule

import com.dms.sms.feature.schedule.model.ScheduleDateModel
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate() : ScheduleDateModel {
    val date = Date(System.currentTimeMillis())
    val formatter = SimpleDateFormat("yyyy.MM", Locale.KOREA).format(date)
    val yearAndMonth = formatter.split(".")
    return ScheduleDateModel(yearAndMonth[0].toInt(), yearAndMonth[1].toInt())
}
fun getCurrentDay() : String {
    val date = Date(System.currentTimeMillis())
    return SimpleDateFormat("dd", Locale.KOREA).format(date).toInt().toString()
}
fun getCurrentMonth() : String {
    val date = Date(System.currentTimeMillis())
    return SimpleDateFormat("MM", Locale.KOREA).format(date).toInt().toString()
}