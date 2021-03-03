package com.dms.sms.feature.schedule.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.databinding.ItemSchoolScheduleCalenderBinding
import com.dms.sms.databinding.ItemSchoolScheduleEmptyCalenderBinding
import com.dms.sms.feature.schedule.model.Day
import com.dms.sms.feature.schedule.model.ScheduleModel
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import java.text.SimpleDateFormat
import java.util.*

class SchoolCalendarSelectionAdapter(
    private val days: List<Day>,
    private val viewModel: SchoolScheduleViewModel
) : RecyclerView.Adapter<SchoolEmptyCalendarViewHolder>() {

    var emptyDays: Int = 0
    var selectedDay: Int? = null
    var previousDay: Int? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolEmptyCalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSchoolScheduleEmptyCalenderBinding.inflate(inflater, parent, false)
        return SchoolEmptyCalendarViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: SchoolEmptyCalendarViewHolder, position: Int) {
        Log.d("smsddd", position.toString())
        try {
            if (position > emptyDays) {
                holder.bindSchedule(days[position - 1], days[position], selectedDay)
                Log.d("smsddd", "bindschedule")
            }
            else{
                holder.bind(days[position])
                Log.d("smsddd", "bind")
            }

        } catch (e: Exception) {
            holder.bind(days[position])
            Log.d("smsddd", "exceptionnnn")
        }
    }

    override fun getItemCount(): Int = days.size

    fun setCalendar(list: List<ScheduleModel>, year: Int, month: Int) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy년 M월", Locale.KOREAN)
        calendar.time = sdf.parse("${year}년 ${month}월")!!
        emptyDays = 5 + calendar.get(Calendar.DAY_OF_WEEK)
        Log.d("smsddd","setcal")

        list.forEach {
            if (it.startMonth < it.endMonth && it.endMonth == viewModel.currentMonth.value) {
                for (i in 1..it.endDay) {
                    days[emptyDays + i].schedule.add(it)
                }
            } else if (it.startMonth < it.endMonth && it.startMonth == viewModel.currentMonth.value) {
                for (i in it.startDay..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    days[emptyDays + i].schedule.add(it)
                }
            } else {
                for (i in it.startDay..it.endDay) {
                    days[emptyDays + i].schedule.add(it)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun selectDay(selectedDay: Int) {

        previousDay = this.selectedDay
        this.selectedDay = selectedDay

        if (previousDay != null) {
            notifyItemChanged(previousDay!! + emptyDays)
        }
        notifyItemChanged(this.selectedDay!! + emptyDays)

    }


}

class SchoolEmptyCalendarViewHolder(
    private val binding: ItemSchoolScheduleEmptyCalenderBinding,
    private val viewModel: SchoolScheduleViewModel
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(day: Day) {
        Log.d("bindday", day.toString())
        binding.vm = viewModel
        binding.model = day
        binding.day = day.date
        binding.executePendingBindings()
    }

    fun bindSchedule(previousDay: Day, today: Day, selectedDay: Int? = null) {
        Log.d("binddaysche", today.toString())
        Log.d("binddayschepr", previousDay.toString())

        if (selectedDay.toString() == today.date) {
            binding.selectedStateImg.visibility = View.VISIBLE
            viewModel.selectedDateSchedule.value = today.schedule
        }
        else {
            binding.selectedStateImg.visibility = View.INVISIBLE
        }
        binding.vm = viewModel
        binding.day = today.date
        binding.model = today
        binding.executePendingBindings()


    }



}