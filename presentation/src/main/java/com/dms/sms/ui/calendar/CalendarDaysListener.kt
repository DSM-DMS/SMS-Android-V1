package com.dms.sms.ui.calendar

import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

interface CalendarDaysListener {
    var beforeImv: ImageView
    var afterImv: ImageView
    var titleTv: TextView
    var daysGrid: GridView

    var selectedTv: TextView?

    var eventListener: UserListener?

    var calendar: Calendar
    val sdf: SimpleDateFormat

    var year: Int
    var month: Int
    var selectedDay: Int

    val eventDays: ArrayList<String>


    fun selectDay(day: Int)
}