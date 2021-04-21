package com.dms.sms.feature.schedule

import com.dms.sms.feature.schedule.model.CalendarTime
import com.dms.sms.feature.schedule.model.Day
import com.dms.sms.feature.schedule.model.ScheduleDateModel
import java.text.SimpleDateFormat
import java.util.*

fun generateMonth(scheduleDate : ScheduleDateModel): List<Day> {
    val calendar  = Calendar.getInstance()
    val monthSchedule = mutableListOf<Day>()
    val sdf = SimpleDateFormat("yyyy년 M월", Locale.KOREAN)
    calendar.time = sdf.parse("${scheduleDate.year}년 ${scheduleDate.month}월")!!
    monthSchedule.add(Day("S"))
    monthSchedule.add(Day("M"))
    monthSchedule.add(Day("T"))
    monthSchedule.add(Day("W"))
    monthSchedule.add(Day("T"))
    monthSchedule.add(Day("F"))
    monthSchedule.add(Day("S"))

    repeat((1 until calendar.get(Calendar.DAY_OF_WEEK)).count()) {
        monthSchedule.add(Day(""))
    }
    for (i in 1 .. calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
        val day= Day(i.toString())
        monthSchedule.add(day)
    }

    return monthSchedule
}

fun generateEmptyMonth(scheduleDate : ScheduleDateModel): List<Day> {
    val calendar  = Calendar.getInstance()
    val monthSchedule = mutableListOf<Day>()
    val sdf = SimpleDateFormat("yyyy년 M월", Locale.KOREAN)
    calendar.time = sdf.parse("${scheduleDate.year}년 ${scheduleDate.month}월")!!
    monthSchedule.add(Day(""))
    monthSchedule.add(Day(""))
    monthSchedule.add(Day(""))
    monthSchedule.add(Day(""))
    monthSchedule.add(Day(""))
    monthSchedule.add(Day(""))
    monthSchedule.add(Day(""))

    repeat((1 until calendar.get(Calendar.DAY_OF_WEEK)).count()) {
        monthSchedule.add(Day(""))
    }
    for (i in 1 .. calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
        val day= Day(i.toString())
        monthSchedule.add(day)
    }

    return monthSchedule
}

fun calculateTime(year : Int, month : Int) : CalendarTime {
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