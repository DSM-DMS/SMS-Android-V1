package com.dms.sms.feature.timetable.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.ItemTimeTableBinding

class TimeTableAdapter : RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder>() {
    private var timeTableListItems = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        val binding =
            ItemTimeTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TimeTableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        holder.bind(timeTableListItems[position])
    }

    override fun getItemCount(): Int =
        timeTableListItems.size

    fun setItems(timeTableList: MutableLiveData<ArrayList<String>>) {
        this.timeTableListItems = timeTableList.value!!
        notifyDataSetChanged()
    }

    inner class TimeTableViewHolder(private val binding: ItemTimeTableBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.item = item
        }
    }
}