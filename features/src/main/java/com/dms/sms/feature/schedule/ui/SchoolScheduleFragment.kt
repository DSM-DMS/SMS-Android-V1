package com.dms.sms.feature.schedule.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import com.dms.sms.base.BackPressedBaseFragment
import com.dms.sms.databinding.FragmentSchoolScheduleBinding
import com.dms.sms.feature.schedule.adapter.DetailScheduleAdapter
import com.dms.sms.feature.schedule.adapter.SchoolCalendarAdapter
import com.dms.sms.feature.schedule.adapter.SchoolCalendarSelectionAdapter
import com.dms.sms.feature.schedule.generateEmptyMonth
import com.dms.sms.feature.schedule.generateMonth
import com.dms.sms.feature.schedule.getCurrentDate
import com.dms.sms.feature.schedule.model.ScheduleDateModel
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SchoolScheduleFragment : BackPressedBaseFragment<FragmentSchoolScheduleBinding>() {
    override val viewModel: SchoolScheduleViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_school_schedule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
        initView()
    }

    override fun observeEvents() {
        viewModel.currentMonth.observe(viewLifecycleOwner,  {
            it?.let {
                binding.schoolCalendarRv.adapter = SchoolCalendarAdapter(generateMonth(ScheduleDateModel(viewModel.currentYear.value!!,it)),viewModel)
                binding.schoolCalendarSelectionRv.adapter = SchoolCalendarSelectionAdapter(generateEmptyMonth(ScheduleDateModel(viewModel.currentYear.value!!,it)),viewModel)

            }

        })
        viewModel.selectedDateSchedule.observe(viewLifecycleOwner, {
            it?.let {
                if(it.isEmpty())
                    binding.detailSchoolScheduleRv.adapter = DetailScheduleAdapter()
                else
                    (binding.detailSchoolScheduleRv.adapter as DetailScheduleAdapter).updateDetailSchedule(it)
            }
        })

        viewModel.onClickTimeTableSwitch.observe(viewLifecycleOwner, {
            navigateFragment(R.id.action_global_timeTableFragment)
        })
    }


    private fun initView(){
        binding.schoolCalendarRv.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.schoolCalendarRv.adapter = SchoolCalendarAdapter(generateMonth(getCurrentDate()),viewModel)

        binding.schoolCalendarSelectionRv.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.schoolCalendarSelectionRv.adapter = SchoolCalendarSelectionAdapter(generateEmptyMonth(getCurrentDate()),viewModel)

        binding.detailSchoolScheduleRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.detailSchoolScheduleRv.adapter = DetailScheduleAdapter()
    }


}