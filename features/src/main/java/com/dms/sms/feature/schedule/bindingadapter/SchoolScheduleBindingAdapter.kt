package com.dms.sms.feature.schedule.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.feature.schedule.adapter.SchoolCalendarAdapter
import com.dms.sms.feature.schedule.adapter.SchoolCalendarSelectionAdapter
import com.dms.sms.feature.schedule.model.ScheduleModel


@BindingAdapter("schedules", "year", "month", requireAll = false)
fun RecyclerView.setSchedules(schedules: List<ScheduleModel>?, year: Int, month: Int) {
    if (adapter != null && schedules != null)
        (adapter as SchoolCalendarAdapter).setCalendar(schedules, year, month)
}

@BindingAdapter("empty_schedules", "empty_year", "empty_month", requireAll = false)
fun RecyclerView.setEmptySchedules(schedules: List<ScheduleModel>?, year: Int, month: Int) {
    if (adapter != null && schedules != null)
        (adapter as SchoolCalendarSelectionAdapter).setCalendar(schedules, year, month)
}

@BindingAdapter("selectedDay")
fun RecyclerView.selectDay(selectedDay: String?) {
    if (adapter != null && selectedDay != null) {
        try {
            (adapter as SchoolCalendarSelectionAdapter).selectDay(selectedDay.toInt())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}