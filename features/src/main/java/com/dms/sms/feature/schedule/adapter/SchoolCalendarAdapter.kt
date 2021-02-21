package com.dms.sms.feature.schedule.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import com.dms.sms.databinding.ItemSchoolScheduleCalenderBinding
import com.dms.sms.feature.schedule.model.Day
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import com.dms.sms.feature.schedule.model.ScheduleModel
import java.text.SimpleDateFormat
import java.util.*

class SchoolCalendarAdapter(
    private val days: List<Day>,
    private val viewModel: SchoolScheduleViewModel
) : RecyclerView.Adapter<SchoolCalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolCalendarViewHolder {
        return SchoolCalendarViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: SchoolCalendarViewHolder, position: Int) {
        Log.d("regen",position.toString() + " "+viewModel.currentMonth.value)
        Log.d("gogo",position.toString() + " "+days[position])

        try {
            holder.bindSchedule(days[position - 1], days[position])
        } catch (e: Exception) {
            holder.bind(days[position])
        }
    }

    override fun getItemCount(): Int = days.size

    fun setCalendar(list: List<ScheduleModel>, year: Int, month: Int) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy년 M월", Locale.KOREAN)
        calendar.time = sdf.parse("${year}년 ${month}월")!!
        val emptyDays = 5 + calendar.get(Calendar.DAY_OF_WEEK)


        list.forEach {
            if(it.endDate<it.startDate){
                for (i in it.startDate..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    days[emptyDays + i].schedule.add(it)
                }
            }
            else {
                for (i in it.startDate..it.endDate) {
                    days[emptyDays + i].schedule.add(it)
                }
            }
        }

        notifyDataSetChanged()

    }


}

class SchoolCalendarViewHolder(
    private val binding: ItemSchoolScheduleCalenderBinding,
    private val viewModel: SchoolScheduleViewModel
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, viewModel: SchoolScheduleViewModel): SchoolCalendarViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemSchoolScheduleCalenderBinding.inflate(layoutInflater, parent, false)
            return SchoolCalendarViewHolder(binding, viewModel)
        }
    }

    fun bind(day: Day) {
        binding.vm = viewModel
        binding.model = day
        binding.executePendingBindings()

    }

    fun bindSchedule(previousDay: Day, today: Day) {
//        if (viewModel.isSelected.value != null && viewModel.isSelected.value == adapterPosition)
//            binding.dayHeadLayout.background = ContextCompat.getDrawable(binding.dayHeadLayout.context, R.drawable.square_background)
//        else
//            binding.dayHeadLayout.setBackgroundColor(binding.dayHeadLayout.context.getColor(R.color.colorWhite))

        bindSameScheduleAsYesterday(previousDay, today)
        bindTodaySchedule(today)


        binding.vm = viewModel
        binding.position = adapterPosition
        binding.model = today
        binding.executePendingBindings()


    }

    private fun bindSameScheduleAsYesterday(previousDay: Day, today: Day) {

        for (i in 0 until today.schedule.size) {
            for (j in 0 until previousDay.schedule.size) {
                if (today.schedule[i].scheduleUUID == previousDay.schedule[j].scheduleUUID) {
                    if (j > 2)
                        break
                    when (previousDay.schedule[j].datePosition) {
                        0 -> {
                            binding.firstScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorPurple,
                                    null
                                )
                            )
                        }
                        1 -> {
                            binding.secondScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorOrange2,
                                    null
                                )
                            )
                        }
                        2 -> {
                            binding.thirdScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorYellow2,
                                    null
                                )
                            )
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
                    when {
                        list.containsAll(listOf(0, 1)) -> {
                            binding.thirdScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorYellow2,
                                    null
                                )
                            )
                            val layoutParams =
                                binding.thirdScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.thirdScheduleView.layoutParams = layoutParams

                            today.schedule[i].datePosition = 2
                            list.add(2)
                        }
                        list.containsAll(listOf(0, 2)) -> {
                            binding.secondScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorOrange2,
                                    null
                                )
                            )
                            val layoutParams =
                                binding.secondScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.secondScheduleView.layoutParams = layoutParams

                            today.schedule[i].datePosition = 1
                            list.add(1)


                        }
                        list.containsAll(listOf(1, 2)) -> {
                            binding.firstScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorPrimary,
                                    null
                                )
                            )

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
                            binding.secondScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorOrange2,
                                    null
                                )
                            )
                            val layoutParams =
                                binding.secondScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.secondScheduleView.layoutParams = layoutParams

                            today.schedule[i].datePosition = 1
                            list.add(1)

                        }
                        list.contains(1) -> {
                            binding.firstScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorPrimary,
                                    null
                                )
                            )
                            val layoutParams =
                                binding.firstScheduleView.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.leftMargin = 5
                            binding.firstScheduleView.layoutParams = layoutParams

                            today.schedule[i].datePosition = 0
                            list.add(0)

                        }
                        list.contains(2) -> {
                            binding.firstScheduleView.setBackgroundColor(
                                binding.root.resources.getColor(
                                    R.color.colorPrimary,
                                    null
                                )
                            )
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
                    binding.firstScheduleView.setBackgroundColor(
                        binding.root.resources.getColor(
                            R.color.colorPrimary,
                            null
                        )
                    )
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

