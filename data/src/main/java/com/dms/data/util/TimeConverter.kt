package com.dms.data.util

import java.text.SimpleDateFormat
import java.util.*


fun Long.convertTime() : String{
    val startDate = Date(this)
    return SimpleDateFormat("MM-dd",Locale.KOREA).format(startDate)
}