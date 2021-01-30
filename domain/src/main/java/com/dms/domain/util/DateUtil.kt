package com.dms.domain.util

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val now = System.currentTimeMillis()
    val date = Date(now)
    val simpleDate = SimpleDateFormat("yyyyMMdd", Locale.KOREA)

    return simpleDate.format(date)
}

fun getDate(timeUnix: Long): String {
    val sd = Date(timeUnix * 1000)
    val simpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

    return simpleDateFormat.format(sd)
}

fun getCurDay(day: Int): String {
    val formatter = SimpleDateFormat("yyyyMMdd", Locale.KOREA)
    val c = Calendar.getInstance()

    when (day) {
        1 -> c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        2 -> c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY)
        3 -> c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY)
        4 -> c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY)
        5 -> c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
    }

    return formatter.format(c.time)
}

fun isMonday(curDate: String): Boolean {
    return curDate == getCurDay(1)
}

fun isToday(timeUnix: Long): Boolean =
    getDate(timeUnix) == getCurrentDate()
