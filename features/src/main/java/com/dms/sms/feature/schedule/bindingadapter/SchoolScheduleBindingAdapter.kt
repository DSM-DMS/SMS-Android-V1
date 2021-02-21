package com.dms.sms.feature.schedule.bindingadapter

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dms.sms.R
import com.dms.sms.feature.schedule.adapter.DetailScheduleAdapter
import com.dms.sms.feature.schedule.adapter.SchoolCalendarAdapter
import com.dms.sms.feature.schedule.model.ScheduleModel


@BindingAdapter("schedules", "year", "month", requireAll = false)
fun RecyclerView.setSchedules(schedules: List<ScheduleModel>?, year: Int, month: Int) {
    if (adapter != null && schedules !=null) {
        Log.d("setsche ",schedules.toString())
        Log.d("setsche yea ",year.toString())
        Log.d("setsche mon ",month.toString())

        (adapter as SchoolCalendarAdapter).setCalendar(schedules, year, month)
    }

}
//
@BindingAdapter("pageChange")
fun ViewPager2.setPageChange(data: LiveData<Int>) {
    if (currentItem != data.value) {
        currentItem = data.value ?: 0

    }
}

@InverseBindingAdapter(attribute = "pageChange")
fun ViewPager2.getPageChange(): Int {
    Log.d("currentItemgpc", currentItem.toString())
    return currentItem
}

@BindingAdapter("pageChangeAttrChanged")
fun ViewPager2.setPageChangeListener(listener: InverseBindingListener) {
    registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            listener.onChange()
        }
    })
}

//@BindingAdapter("selectedDaySchedule", "currentMonth")
//fun RecyclerView.selectDay(selectedDaySchedule: List<ScheduleModel>?, currentMonth : Int) {
//    Log.d("asdf","ddddd")
//   if(selectedDaySchedule!=null){
//       (this.adapter as DetailScheduleAdapter).updateDetailSchedule(selectedDaySchedule, currentMonth)
//   }
//}