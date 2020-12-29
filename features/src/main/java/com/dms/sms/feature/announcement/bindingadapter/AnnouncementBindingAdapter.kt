package com.dms.sms.feature.announcement.bindingadapter

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.feature.announcement.adapter.AnnouncementsAdapter
import com.dms.sms.feature.announcement.model.SimpleAnnouncementModel
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel
import org.w3c.dom.Text
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.editorjskit.ui.EditorJsAdapter
import work.upstarts.editorjskit.ui.theme.EJStyle

@BindingAdapter("vm","announcementItems")
fun RecyclerView.bindAnnouncementItems(viewModel: AnnouncementsViewModel,announcementItems: List<SimpleAnnouncementModel>?) {
    adapter = AnnouncementsAdapter(viewModel)
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    if(!announcementItems.isNullOrEmpty()) {
        (adapter as AnnouncementsAdapter).setAnnouncements(announcementItems)
    }

}

@BindingAdapter("announcement")
fun RecyclerView.bindAnnouncement(announcement : List<EJBlock>?){
    if(adapter != null && !announcement.isNullOrEmpty()) {
        (adapter as EditorJsAdapter).items = announcement
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}

@BindingAdapter("next_post")
fun TextView.isNextPostExists(announcementUUID : String?) {
    if(announcementUUID=="")
      this.text = "다음 글이 없습니다"

}

@BindingAdapter("previous_post")
fun TextView.isPreviousPostExists(announcementUUID : String?) {
    if(announcementUUID=="")
        this.text = "이전 글이 없습니다"

}


//fun RecyclerView.bindAnnouncementPages(announcementPages: List<Int>) {
//    adapter = AnnouncementPageAdapter(announcementPages)
//    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//
//}