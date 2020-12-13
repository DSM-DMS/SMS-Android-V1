package com.dms.sms.feature.calendar

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
    private val adapter : DetailScheduleAdapter,
    context: Context,
    days: ArrayList<Day>) : ArrayAdapter<Day>(context, R.layout.item_school_schedule_calender, days) {

    private val inflater= LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        with(listener) {
            val view = inflater.inflate(R.layout.item_school_schedule_calender, parent, false)
            val day = getItem(position)

            var previousDayEvent: List<Pair<Int?, String?>?>? = null
            var nextDayEvent: List<Pair<Int?, String?>?>? = null


            try {
                previousDayEvent = getItem(position - 1)?.speciality
                nextDayEvent = getItem(position + 1)?.speciality
            }
            catch (e: Exception) {
                e.printStackTrace()
            }

            if (day!!.speciality != null) {


                view.calendar_day_tv.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorBlack
                    )
                )

                if (previousDayEvent != null || nextDayEvent != null) {
                    val previousDaySameEvent = checkPreviousDayHasEvent(previousDayEvent, day.speciality!!)
                    val nextDaySameEvent = checkNextDayHasEvent(nextDayEvent, day.speciality!!)


                    if (previousDaySameEvent && nextDaySameEvent) {
                        view.calendar_day_bg_view.background =
                            ContextCompat.getDrawable(context, R.drawable.square_background)
                    }
                    else if (nextDaySameEvent) {
                        view.calendar_day_bg_view.background =
                            ContextCompat.getDrawable(context, R.drawable.left_round_background)
                    }
                    else if (previousDaySameEvent) {
                        view.calendar_day_bg_view.background =
                            ContextCompat.getDrawable(context, R.drawable.right_round_background)
                    }
                    else {

                        view.calendar_day_bg_view.background =
                            ContextCompat.getDrawable(context, R.drawable.circle_background)

                        view.calendar_day_bg_view.backgroundTintList =
                            context.resources.getColorStateList(R.color.colorLightGray, null)
                    }

                    if (!nextDaySameEvent) {
                        day.speciality?.forEach {
                            setSpecialDay(it!!.first, view)
                        }
                    }
                }

                if(isPublicHoliday(day.speciality!!))
                    setPublicHoliday(view)

            }

            if (day.date != 0) {

                if (day.date is String) {
                    view.calendar_day_tv.setTextColor(context.getColorStateList(R.color.colorBlack))
                    view.calendar_day_tv.text = day.date
                }
                else {
                    view.calendar_day_tv.text = day.date.toString()
                    view.setOnClickListener {
                        if(selectedTv!=null) {
                            selectedTv?.background = null
                            selectedTv?.backgroundTintList =
                                context.resources.getColorStateList(R.color.colorWhite, null)

                            selectedTv?.setTextColor(
                                view.calendar_day_tv.currentTextColor
                            )
                        }
                        if(day.speciality!=null) {
                            adapter.scheduleList =day.speciality
                            adapter.date = day.date
                            adapter.notifyDataSetChanged()
                        }


                        view.calendar_day_tv.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorBlack
                            )
                        )

                        view.calendar_day_tv.background =
                            ContextCompat.getDrawable(context, R.drawable.circle_background)
                        view.calendar_day_tv.backgroundTintList =
                            context.resources.getColorStateList(R.color.colorDarkGray, null)
                            selectDay(day.date as Int)
                            selectedTv = view.calendar_day_tv
                    }
                }
            }

            return view
        }
    }

    private fun checkPreviousDayHasEvent(previousDay : List<Pair<Int?,String?>?>?,currentDay: List<Pair<Int?,String?>?>): Boolean{
        if(previousDay==null){
            return false
        }
        currentDay.forEach { curDay->
            previousDay.forEach { preDay ->
                 if(curDay!!.second == preDay!!.second){
                     return true
                 }
            }

        }
        return false
    }
    private fun checkNextDayHasEvent(nextDay : List<Pair<Int?,String?>?>?,currentDay: List<Pair<Int?,String?>?>) : Boolean {
        if (nextDay==null){
            return false
        }
        currentDay.forEach { curDay->
            nextDay.forEach { nDay ->
                if(curDay!!.second == nDay!!.second){
                    return true
                }
            }
        }
        return false

    }
    private fun isPublicHoliday(day : List<Pair<Int?,String?>?>) : Boolean {
        day.forEach {
            if(it!!.first ==0) return true
        }
        return false
    }
    private fun setPublicHoliday(dateView : View){
        dateView.calendar_day_tv.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.colorRed
            )
        )
    }

    private fun setSpecialDay(speciality : Int?, dateView : View){
         when (speciality) {
             1 -> {
                 dateView.school_event_view.visibility = View.VISIBLE
             }
             2 -> {
                 dateView.school_study_event_view.visibility = View.VISIBLE
             }

         }
    }


}