package com.dms.sms.feature.announcement.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.ItemPageBinding
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel

class AnnouncementPageAdapter(private val viewModel: AnnouncementsViewModel) :
    RecyclerView.Adapter<AnnouncementsPageViewHolder>() {
    private var pages = ArrayList<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsPageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPageBinding.inflate(inflater, parent, false)
        return AnnouncementsPageViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: AnnouncementsPageViewHolder, position: Int) {
        holder.bind(pages[position])

    }

    override fun getItemCount(): Int =
        pages.size


    fun setPages(pages: List<String>) {
        this.pages = pages as ArrayList<String>
        notifyDataSetChanged()

    }


}

class AnnouncementsPageViewHolder(
    private val binding: ItemPageBinding,
    private val viewModel: AnnouncementsViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.page = item.toInt()
        binding.vm = viewModel
        binding.executePendingBindings()
    }

}