package com.dms.sms.feature.outing.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.feature.outing.adapter.OutingHistoryAdapter
import com.dms.sms.feature.outing.model.OutingModel

@BindingAdapter("outingHistoryList")
fun RecyclerView.bindOutingList(outingHistoryItems: MutableLiveData<ArrayList<OutingModel>>){
    (adapter as OutingHistoryAdapter).setItems(outingHistoryItems)
}