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
import com.dms.sms.feature.schedule.model.Day
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import com.dms.sms.feature.schedule.model.ScheduleModel
import java.text.SimpleDateFormat
import java.util.*

class SchoolCalendarAdapter(
    private val days: List<Day>,
    private val viewModel: SchoolScheduleViewModel
) : RecyclerView.Adapter<SchoolCalendarViewHolder>() {

    var emptyDays : Int = 0
    var selectedDay : Int? = null
    var previousDay : Int? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolCalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSchoolScheduleCalenderBinding.inflate(inflater, parent, false)
        return SchoolCalendarViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: SchoolCalendarViewHolder, position: Int) {
        try {
            if(position>emptyDays)
                holder.bindSchedule(days[position - 1], days[position], selectedDay)
            else
                holder.bind(days[position])

        } catch (e: Exception) {
            holder.bind(days[position])
        }
    }

    override fun getItemCount(): Int = days.size

    fun setCalendar(list: List<ScheduleModel>, year: Int, month: Int) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy년 M월", Locale.KOREAN)
        calendar.time = sdf.parse("${year}년 ${month}월")!!
        emptyDays = 5 + calendar.get(Calendar.DAY_OF_WEEK)


        list.forEach {
            if(it.startMonth< it.endMonth && it.endMonth==viewModel.currentMonth.value){
                for (i in 1..it.endDay) {
                    days[emptyDays + i].schedule.add(it)
                }
            }
            else if (it.startMonth< it.endMonth && it.startMonth==viewModel.currentMonth.value){
                for (i in it.startDay..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    days[emptyDays + i].schedule.add(it)
                }
            }
            else {
                for (i in it.startDay..it.endDay) {
                    days[emptyDays + i].schedule.add(it)
                }
            }
        }
        notifyDataSetChanged()

    }
    fun selectDay(selectedDay : Int){
//        previousDay = this.selectedDay
//        this.selectedDay = selectedDay
//        if(previousDay!=null){
//            notifyItemChanged(previousDay!! +emptyDays)
//        }
//        notifyItemChanged(selectedDay+emptyDays)
    }
    fun updateCalendar() {
////        val diffCallback = DiffCallback(listOf(Day("")),days)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        diffResult.dispatchUpdatesTo(this)
    }


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
    fun bindSchedule(previousDay: Day, today: Day, selectedDay: Int? = null) {
        Log.d("binddaysche", today.toString())

        bindSameScheduleAsYesterday(previousDay, today)
        bindTodaySchedule(today)

        if(selectedDay.toString() == today.date) {
            binding.selectedStateImg.visibility = View.VISIBLE
        }
        else
            binding.selectedStateImg.visibility= View.INVISIBLE

        binding.vm = viewModel
        binding.day = today.date
        binding.model = today
        binding.executePendingBindings()


    }
    private fun bindSameScheduleAsYesterday(previousDay: Day, today: Day){

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
    private fun bindTodaySchedule(today: Day){
        val list: MutableList<Int> = mutableListOf()
        for (i in 0 until today.schedule.size) {

            if (i > 2)
                break
            if (today.schedule[i].datePosition != -1) {
                list.add(today.schedule[i].datePosition)
                continue
            }
            else {

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

 class DiffCallback(private val oldList : List<Day>, private val newList : List<Day>) : DiffUtil.Callback() {


     override fun getOldListSize(): Int = oldList.size

     override fun getNewListSize(): Int = newList.size

     override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
         return oldList[oldItemPosition].isSelected == oldList[oldItemPosition].isSelected
     }

     override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
         return oldList[oldItemPosition].isSelected == oldList[oldItemPosition].isSelected
     }
 }