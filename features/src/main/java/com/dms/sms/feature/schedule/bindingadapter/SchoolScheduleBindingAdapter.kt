package com.dms.sms.feature.schedule.bindingadapter

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.feature.schedule.adapter.SchoolCalendarAdapter
import com.dms.sms.feature.schedule.adapter.SchoolCalendarSelectionAdapter
import com.dms.sms.feature.schedule.model.ScheduleModel
import java.lang.Exception


@BindingAdapter("schedules","year","month",requireAll = false)
fun RecyclerView.setSchedules(schedules : List<ScheduleModel>?,year: Int, month: Int){
    if (adapter!=null && schedules!=null)
        (adapter as SchoolCalendarAdapter).setCalendar(schedules, year, month)


}
@BindingAdapter("empty_schedules","empty_year","empty_month",requireAll = false)
fun RecyclerView.setEmptySchedules(schedules : List<ScheduleModel>?,year: Int, month: Int){
    Log.d("fffffff","fffffff")
    if (adapter!=null && schedules!=null)
        (adapter as SchoolCalendarSelectionAdapter).setCalendar(schedules,year, month)


}

@BindingAdapter("selectedDay")
fun RecyclerView.selectDay(selectedDay : String?) {
    Log.d("jjjjj",selectedDay.toString())
    if (adapter != null && selectedDay != null) {
        try {
            (adapter as SchoolCalendarSelectionAdapter).selectDay(selectedDay.toInt())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
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
//}