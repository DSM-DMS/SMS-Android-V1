package com.dms.sms.feature.timetable.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.feature.timetable.adapter.TimeTableAdapter

@BindingAdapter("timeTableList")
fun RecyclerView.bindTimeTable(timeTableList: MutableLiveData<ArrayList<String>>) {
    (adapter as TimeTableAdapter).setItems(timeTableList)
}