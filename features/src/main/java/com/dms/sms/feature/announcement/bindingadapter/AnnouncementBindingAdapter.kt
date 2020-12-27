package com.dms.sms.feature.announcement.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.feature.announcement.adapter.AnnouncementPageAdapter
import com.dms.sms.feature.announcement.adapter.AnnouncementsAdapter
import com.dms.sms.feature.announcement.model.SimpleAnnouncementModel
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel

@BindingAdapter("vm","announcementItems")
fun RecyclerView.bindAnnouncementItems(viewModel: AnnouncementsViewModel,announcementItems: List<SimpleAnnouncementModel>?) {
    adapter = AnnouncementsAdapter(viewModel)
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    if(!announcementItems.isNullOrEmpty()) {
        (adapter as AnnouncementsAdapter).setAnnouncements(announcementItems)
    }

}

fun RecyclerView.bindAnnouncementPages(announcementPages: List<Int>) {
    adapter = AnnouncementPageAdapter(announcementPages)
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

}