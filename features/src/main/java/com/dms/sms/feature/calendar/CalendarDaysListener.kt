package com.dms.sms.feature.calendar

import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

interface CalendarDaysListener {

    var daysGrid: GridView
    var selectedTv: TextView?

    var tvColor : Int?

    var calendar: Calendar
    val sdf: SimpleDateFormat

    var year: Int
    var month: Int
    var selectedDay: Int



    fun selectDay(day: Int)
}