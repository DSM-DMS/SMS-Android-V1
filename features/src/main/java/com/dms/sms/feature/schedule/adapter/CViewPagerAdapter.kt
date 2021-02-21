package com.dms.sms.feature.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.FragmentCalendarBinding
import com.dms.sms.feature.schedule.generateMonth
import com.dms.sms.feature.schedule.model.ScheduleDateModel
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel

class CViewPagerAdapter(private val viewModel: SchoolScheduleViewModel) : RecyclerView.Adapter<ViewHolder>() {
    private val pages = List<Int>(10) { index -> index }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = pages.size


}

class ViewHolder(private val binding: FragmentCalendarBinding, private val viewModel: SchoolScheduleViewModel) : RecyclerView.ViewHolder(binding.root){
    companion object {
        fun from(parent: ViewGroup, viewModel: SchoolScheduleViewModel): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FragmentCalendarBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding, viewModel)
        }
    }
    fun bind(position: Int){
        binding.vm = viewModel
        binding.schoolCalendarRv.layoutManager = GridLayoutManager(binding.root.context, 7)
        binding.schoolCalendarRv.adapter = SchoolCalendarAdapter(generateMonth(ScheduleDateModel(viewModel.currentYear.value!!,viewModel.currentMonth.value!!)),viewModel)
        binding.executePendingBindings()
    }
}