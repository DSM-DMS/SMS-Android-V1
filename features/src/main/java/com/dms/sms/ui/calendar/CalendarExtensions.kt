package com.dms.sms.ui.calendar

fun calculateTime(year : Int, month : Int) : CalendarTime{
    return when {
        month > 12 -> {
            CalendarTime(year+1, 1)
        }
        month < 1 -> {
            CalendarTime(year - 1, 12)

        }
        else -> {
            CalendarTime(year, month)

        }
    }
}