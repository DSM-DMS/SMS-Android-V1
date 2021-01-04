package com.dms.domain.util

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val now = System.currentTimeMillis()
    val date = Date(now)
    val simpleDate = SimpleDateFormat("yyyyMMdd", Locale.KOREA)

    return simpleDate.format(date)
}

fun getDate(timeUnix: Long): String{
    val date = Date(timeUnix)
    val simpleDateFormat = SimpleDateFormat("yyyyMMdd",Locale.KOREA)

    return simpleDateFormat.format(date)
}