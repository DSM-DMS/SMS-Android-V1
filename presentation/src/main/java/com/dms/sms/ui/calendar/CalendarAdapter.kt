package com.dms.sms.ui.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.dms.sms.R
import kotlinx.android.synthetic.main.item_school_schedule_calender.view.*
import kotlin.collections.ArrayList

class CalendarAdapter(
    private val listener: CalendarDaysListener,
    context: Context,
    days: ArrayList<Any>) : ArrayAdapter<Any>(context, R.layout.item_school_schedule_calender, days) {

    private val inflater= LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        with(listener){
            val view = inflater.inflate(R.layout.item_school_schedule_calender,parent,false)
            val day= getItem(position)
            if(day!=0) {
                if (day is String) {
                    view.calendar_day_tv.setTextColor(context.getColorStateList(R.color.colorBlack))
                    view.calendar_day_tv.text = day
                }
                else{
                    view.calendar_day_tv.text = day.toString()
                    view.setOnClickListener {
                        selectedTv?.background = null
                        selectedTv?.backgroundTintList = context.resources.getColorStateList(R.color.colorWhite, null)
                        selectedTv?.setTextColor(ContextCompat.getColor(context, R.color.colorLightGray))

                        view.calendar_day_tv.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))

                        view.calendar_day_tv.background = context.getDrawable(R.drawable.circle_background)
                        view.calendar_day_tv.backgroundTintList = context.resources.getColorStateList(R.color.colorPurple, null)

                        selectDay(day as Int)
                        selectedTv= view.calendar_day_tv
                    }
                }
            }






//            if (view is TextView){
//                val day = getItem(position)
//
//                if (day != 0) {
//                    view.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
//                    eventDays.forEach { eventDay ->
//                        if ("${year}년 ${month}월 ${day}일" == eventDay) {
//                            view.setTextColor(ContextCompat.getColor(context, R.color.main_900))
//                        }
//                    }
//                    view.gravity = Gravity.CENTER
//                    view.text = day.toString()
//                }
//
//                if (day is Int && day != 0) {
//                    if ("${year}년 ${month}월 ${day}일" == today && isBig)
//                        view.background = context.getDrawable(R.drawable.calendar_day_stroke)
//
//                    view.setOnClickListener {
//                        selectedTv?.let { tv ->
//
//                            tv.background = context.getDrawable(R.drawable.circle_background)
//                            tv.backgroundTintList =context.getColorStateList(R.color.colorPurple)
//                            tv.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
//                            eventDays.forEach { eventDay ->
//                                if ("${year}년 ${month}월 ${tv.text}일" == eventDay) {
//                                    tv.setTextColor(ContextCompat.getColor(context, R.color.main_900))
//                                }
//                            }
//                        }
//
//                        view.setTextColor(ContextCompat.getColor(context, R.color.black_100))
//
//                            view.background = context.getDrawable(R.drawable.big_calendar_day_solid)
//
//
//                        selectedDay(day)
//
//                        selectedTv = view
//
//                    }
//                }
//            }

            return view
        }
    }
}