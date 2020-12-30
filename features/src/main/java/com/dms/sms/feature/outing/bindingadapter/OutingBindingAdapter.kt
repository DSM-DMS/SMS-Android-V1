package com.dms.sms.feature.outing.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.feature.outing.adapter.OutingHistoryAdapter
import com.dms.sms.feature.outing.adapter.SearchPlaceAdapter
import com.dms.sms.feature.outing.model.OutingModel
import com.dms.sms.feature.outing.model.PlaceListModel
import com.dms.sms.util.GlideApp

@BindingAdapter("outingHistoryList")
fun RecyclerView.bindOutingList(outingHistoryItems: MutableLiveData<ArrayList<OutingModel>>) {
    (adapter as OutingHistoryAdapter).setItems(outingHistoryItems)
}

@BindingAdapter("searchPlaceList")
fun RecyclerView.bindPlaceList(searchPlaceItems: MutableLiveData<ArrayList<PlaceListModel>>) {
    (adapter as SearchPlaceAdapter).setItems(searchPlaceItems)
}

@BindingAdapter("setProfile")
fun ImageView.setProfile(profileUri: String?) {
    if (profileUri != null) {
        GlideApp.with(context)
            .load("https://dsm-sms-s3.s3.ap-northeast-2.amazonaws.com/$profileUri")
            .into(this)
    }
}