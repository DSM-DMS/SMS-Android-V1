package com.dms.data.util

import java.text.SimpleDateFormat
import java.util.*


fun Long.convertTimeToMonthAndDay() : String{
    val startDate = Date(this)
    return SimpleDateFormat("MM-dd",Locale.KOREA).format(startDate)
}



fun Long.convertTimeToDay() : String{
    val startDate = Date(this)
    return SimpleDateFormat("dd",Locale.KOREA).format(startDate)
}
fun Long.convertTimeToMonth() : String{
    val startDate = Date(this)
    return SimpleDateFormat("MM",Locale.KOREA).format(startDate)
}

fun Long.convertTimeToDate() : String{
    val startDate = Date(this)
    return SimpleDateFormat("yyyy.MM.dd",Locale.KOREA).format(startDate)
}