package com.dms.sms.feature.schedule.bindingadapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import com.dms.sms.feature.schedule.adapter.SchoolCalendarAdapter
import com.dms.sms.feature.schedule.model.ScheduleModel
import java.lang.Exception


@BindingAdapter("schedules","year","month",requireAll = false)
fun RecyclerView.setSchedules(schedules : List<ScheduleModel>?,year: Int, month: Int){
    if (adapter!=null && schedules!=null)
        (adapter as SchoolCalendarAdapter).setCalendar(schedules, year, month)


}

@BindingAdapter(value = ["selectedDay", "date"], requireAll = false)
fun View.selectDay(selectedDay : String?, date : String) {
    try {
        selectedDay?.toInt()
        if (selectedDay==date) {
            this.background = ContextCompat.getDrawable(this.context, R.drawable.square_background)
        }
        else {
            this.setBackgroundColor(this.context.getColor(R.color.colorWhite))

        }
    }
    catch (e: Exception){
        this.setBackgroundColor(this.context.getColor(R.color.colorWhite))
    }

}