package com.dms.sms.feature.announcement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.ItemAnnouncementBinding
import com.dms.sms.feature.announcement.model.SimpleAnnouncementModel
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel

class AnnouncementsAdapter(private val viewModel: AnnouncementsViewModel ) : RecyclerView.Adapter<AnnouncementsViewHolder>() {
    private var announcements = ArrayList<SimpleAnnouncementModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAnnouncementBinding.inflate(inflater, parent, false)
        return AnnouncementsViewHolder(binding, viewModel)

    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        holder.bind(announcements[position])

    }

    override fun getItemCount(): Int =
        announcements.size

    fun setAnnouncements(announcements : List<SimpleAnnouncementModel>){
        this.announcements = announcements as ArrayList<SimpleAnnouncementModel>
        notifyDataSetChanged()

    }


}

class AnnouncementsViewHolder(private val binding : ItemAnnouncementBinding, private val viewModel: AnnouncementsViewModel) : RecyclerView.ViewHolder(binding.root){
    fun bind(item : SimpleAnnouncementModel){
        if(item.isChecked==0)
            binding.noticeItemRenewalTv.visibility = View.VISIBLE

        binding.model = item
        binding.vm = viewModel
        binding.executePendingBindings()
    }

}
