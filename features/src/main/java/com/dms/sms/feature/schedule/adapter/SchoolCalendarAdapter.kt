package com.dms.sms.feature.schedule.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import com.dms.sms.databinding.ItemSchoolScheduleCalenderBinding
import com.dms.sms.feature.schedule.getCurrentDate
import com.dms.sms.feature.schedule.getCurrentDay
import com.dms.sms.feature.schedule.getCurrentMonth
import com.dms.sms.feature.schedule.model.Day
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import com.dms.sms.feature.schedule.model.ScheduleModel
import java.text.SimpleDateFormat
import java.util.*

class SchoolCalendarAdapter(
    private val days: List<Day>,
    private val viewModel: SchoolScheduleViewModel
) : RecyclerView.Adapter<SchoolCalendarViewHolder>() {

    var emptyDays: Int = 0
    var selectedDay: Int? = null
    var previousDay: Int? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolCalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSchoolScheduleCalenderBinding.inflate(inflater, parent, false)
        return SchoolCalendarViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: SchoolCalendarViewHolder, position: Int) {
        Log.d("smsddd", position.toString())
        try {
            if (position > emptyDays) {
                holder.bindSchedule(days[position - 1], days[position])
                Log.d("smsddd","bindschedule")
            }
            else{
                holder.bind(days[position])
                Log.d("smsddd","bind")
            }

        } catch (e: Exception) {
            holder.bind(days[position])
            Log.d("smsddd","exceptionnnn")
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
//
//    fun selectDay(selectedDay: Int) {
//
//        previousDay = this.selectedDay
//        this.selectedDay = selectedDay
//
//        if (previousDay != null) {
//            notifyItemChanged(previousDay!! + emptyDays)
//        }
//        notifyItemChanged(this.selectedDay!! + emptyDays)
//
//    }


}

class SchoolCalendarViewHolder(
    private val binding: ItemSchoolScheduleCalenderBinding,
    private val viewModel: SchoolScheduleViewModel
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(day: Day) {
        Log.d("bindday", day.toString())
        binding.vm = viewModel
        binding.model = day
        binding.day = day.date
        binding.executePendingBindings()
    }

    fun bindSchedule(previousDay: Day, today: Day) {
        Log.d("binddaysche", today.toString())
        Log.d("binddayschepr", previousDay.toString())
        if (!today.schedule.isNullOrEmpty()) {
            bindSameScheduleAsYesterday(previousDay, today)
            bindTodaySchedule(today)
        }

//        if (selectedDay.toString() == today.date) {
//            binding.selectedStateImg.visibility = View.VISIBLE
//            viewModel.selectedDateSchedule.value = today.schedule
//        }
//        else {
//            binding.selectedStateImg.visibility = View.INVISIBLE
//        }
        binding.vm = viewModel
        binding.day = today.date
        binding.model = today
        binding.executePendingBindings()


    }

    private fun bindSameScheduleAsYesterday(previousDay: Day, today: Day) {

        Log.d("bssay", today.schedule.toString())
        for (i in 0 until today.schedule.size) {
            for (j in 0 until previousDay.schedule.size) {
                if (today.schedule[i].scheduleUUID == previousDay.schedule[j].scheduleUUID) {
                    today.schedule[i].datePosition = previousDay.schedule[j].datePosition
                    if (j > 2)
                        break
                    when (previousDay.schedule[j].datePosition) {
                        0 -> {
                            Log.d("bssay 0", previousDay.schedule[j].datePosition.toString())
                            binding.firstScheduleView.visibility = View.VISIBLE
                        }
                        1 -> {
                            Log.d("bssay 1", previousDay.schedule[j].datePosition.toString())
                            binding.secondScheduleView.visibility = View.VISIBLE
                        }
                        2 -> {
                            Log.d("bssay 2", previousDay.schedule[j].datePosition.toString())
                            binding.thirdScheduleView.visibility = View.VISIBLE
                        }
                    }

                }
            }
        }
    }

    private fun bindTodaySchedule(today: Day) {
        val list: MutableList<Int> = mutableListOf()

        for (i in 0 until today.schedule.size) {
            if (i > 2)
                break
            if (today.schedule[i].datePosition != -1) {
                list.add(today.schedule[i].datePosition)
                continue
            } else {

                if (list.size > 1) {
                    Log.d("bts", list.toString())
                    when {
                        list.containsAll(listOf(0, 1)) -> {
                            Log.d("bts 0", today.schedule[i].datePosition.toString())
                            binding.thirdScheduleView.visibility = View.VISIBLE
                            val layoutParams =
                                binding.thirdScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.thirdScheduleView.layoutParams = layoutParams

                            today.schedule[i].datePosition = 2
                            list.add(2)
                        }
                        list.containsAll(listOf(0, 2)) -> {
                            Log.d("bts 1", today.schedule[i].datePosition.toString())
                            binding.secondScheduleView.visibility = View.VISIBLE
                            val layoutParams =
                                binding.secondScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.secondScheduleView.layoutParams = layoutParams

                            today.schedule[i].datePosition = 1
                            list.add(1)


                        }
                        list.containsAll(listOf(1, 2)) -> {
                            Log.d("bts 2", today.schedule[i].datePosition.toString())
                            binding.firstScheduleView.visibility = View.VISIBLE

                            val layoutParams =
                                binding.firstScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.firstScheduleView.layoutParams = layoutParams
                            today.schedule[i].datePosition = 0
                            list.add(0)


                        }
                    }
                }
                if (list.size == 1) {
                    when {
                        list.contains(0) -> {
                            Log.d("bts 4", today.schedule[i].datePosition.toString())
                            binding.secondScheduleView.visibility = View.VISIBLE
                            val layoutParams =
                                binding.secondScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.secondScheduleView.layoutParams = layoutParams

                            today.schedule[i].datePosition = 1
                            list.add(1)

                        }
                        list.contains(1) -> {
                            Log.d("bts 5", today.schedule[i].datePosition.toString())
                            binding.firstScheduleView.visibility = View.VISIBLE
                            val layoutParams =
                                binding.firstScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.firstScheduleView.layoutParams = layoutParams

                            today.schedule[i].datePosition = 0
                            list.add(0)

                        }
                        list.contains(2) -> {
                            Log.d("bts 6", today.schedule[i].datePosition.toString())
                            binding.firstScheduleView.visibility = View.VISIBLE
                            val layoutParams =
                                binding.firstScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.firstScheduleView.layoutParams = layoutParams


                            today.schedule[i].datePosition = 0
                            list.add(0)

                        }
                    }


                }
                if (list.size == 0) {
                    Log.d("bts 7", today.schedule[i].datePosition.toString())
                    binding.firstScheduleView.visibility = View.VISIBLE
                    val layoutParams =
                        binding.firstScheduleView.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.leftMargin = 5
                    binding.firstScheduleView.layoutParams = layoutParams

                    today.schedule[i].datePosition = 0
                    list.add(0)


                }
            }

        }
    }


}