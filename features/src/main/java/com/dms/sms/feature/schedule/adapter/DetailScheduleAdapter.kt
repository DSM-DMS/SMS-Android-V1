package com.dms.sms.feature.schedule.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import com.dms.sms.databinding.ItemDetailSchoolScheduleBinding
import com.dms.sms.feature.schedule.model.ScheduleModel

class DetailScheduleAdapter(var scheduleList: List<ScheduleModel> = listOf(), private var month : Int = 0) : RecyclerView.Adapter<DetailScheduleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailSchoolScheduleBinding.inflate(inflater, parent, false)
        return DetailScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailScheduleViewHolder, position: Int) {
        holder.bind(scheduleList[position], month)
    }

    fun updateDetailSchedule(newScheduleList : List<ScheduleModel>, month: Int) {
        val diffCallback = ScheduleDiffCallback(scheduleList,newScheduleList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        scheduleList= newScheduleList
        this.month = month
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = scheduleList.size


}

class DetailScheduleViewHolder(private val binding: ItemDetailSchoolScheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ScheduleModel, month: Int) {
        binding.model = item
        when (item.datePosition) {
            0 -> binding.eventView.backgroundTintList = binding.root.context.resources.getColorStateList(R.color.colorPrimary, null)
            1 -> binding.eventView.backgroundTintList = binding.root.context.resources.getColorStateList(R.color.colorOrange2, null)
            2 -> binding.eventView.backgroundTintList = binding.root.context.resources.getColorStateList(R.color.colorYellow2, null)
            else -> binding.eventView.backgroundTintList = binding.root.context.resources.getColorStateList(R.color.colorGray2, null)
        }
        if(item.startDate == item.endDate)
            binding.dateTv.text = month.toString()+"/"+item.startDate.toString()
        else
            binding.dateTv.text = month.toString()+"/"+item.startDate.toString() + " - " + month.toString()+"/"+item.endDate.toString()



    }

}

class ScheduleDiffCallback(private val oldList : List<ScheduleModel>, private val newList : List<ScheduleModel>) : DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        areItemsTheSame(oldItemPosition, newItemPosition)
}