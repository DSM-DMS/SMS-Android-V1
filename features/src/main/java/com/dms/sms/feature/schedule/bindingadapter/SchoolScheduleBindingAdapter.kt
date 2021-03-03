package com.dms.sms.feature.schedule.bindingadapter

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
//@BindingAdapter("schedules","year","month","selectDay","selectedDaySchedule",requireAll = false)
//fun RecyclerView.setSchedules(schedules : MutableLiveData<List<ScheduleModel>>?, year: MutableLiveData<Int>, month: MutableLiveData<Int>, selectDay : MutableLiveData<String?>, selectedDaySchedule :MutableLiveData<List<ScheduleModel>>){
//    if (adapter!=null && schedules!=null && selectDay.value!=null)
//        selectedDaySchedule.value = (adapter as SchoolCalendarAdapter).setCalendar(schedules.value!!, year.value!!, month.value!!, selectDay.value!!.toInt())
//    else if(adapter!=null && schedules!=null)
//        (adapter as SchoolCalendarAdapter).setCalendar(schedules.value!!, year.value!!, month.value!!)
//
//
//}


//@BindingAdapter(value = ["selectedDay", "date"], requireAll = false)
//fun View.selectDay(selectedDay : String?, date : String) {
////    try {
////        selectedDay?.toInt()
////        if (selectedDay==date) {
////            this.background = ContextCompat.getDrawable(this.context, R.drawable.square_background)
////        }
////        else {
////            this.setBackgroundColor(this.context.getColor(R.color.colorWhite))
////
////        }
////    }
////    catch (e: Exception){
////        this.setBackgroundColor(this.context.getColor(R.color.colorWhite))
////    }
//
//}

//@BindingAdapter("selectedDay")
//fun RecyclerView.selectDay(selectedDay : String?) {
//    if(adapter!=null && selectedDay!=null) {
//        try {
//            (adapter as SchoolCalendarAdapter).selectDay(selectedDay.toInt())
//        }catch (e: Exception){
//            e.printStackTrace()
//        }
//    }
//
//    try {
//        selectedDay?.toInt()
//        if (selectedDay == date) {
//            this.background = ContextCompat.getDrawable(this.context, R.drawable.square_background)
//        } else {
//            this.setBackgroundColor(this.context.getColor(R.color.colorWhite))
//
//        }
//    } catch (e: Exception) {
//        this.setBackgroundColor(this.context.getColor(R.color.colorWhite))
//
//    }
