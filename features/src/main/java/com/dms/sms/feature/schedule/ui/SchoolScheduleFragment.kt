package com.dms.sms.feature.schedule.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentSchoolScheduleBinding
import com.dms.sms.feature.schedule.adapter.DetailScheduleAdapter
import com.dms.sms.feature.schedule.adapter.SchoolCalendarAdapter
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import com.dms.sms.feature.schedule.generateMonth
import com.dms.sms.feature.schedule.getCurrentDate
import com.dms.sms.feature.schedule.model.ScheduleDateModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SchoolScheduleFragment : BaseFragment<FragmentSchoolScheduleBinding>() {
    override val viewModel: SchoolScheduleViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_school_schedule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        initView()
    }

    override fun observeEvents() {
        viewModel.currentMonth.observe(viewLifecycleOwner,  {
            it?.let {
                binding.schoolCalendarRv.adapter = SchoolCalendarAdapter(generateMonth(ScheduleDateModel(viewModel.currentYear.value!!,it)),viewModel)
            }

        })
        viewModel.isSelected.observe(viewLifecycleOwner, {
            if(binding.schoolCalendarRv.adapter!=null){
                (binding.schoolCalendarRv.adapter as SchoolCalendarAdapter).notifyDataSetChanged() // 이 부분 주석 처리하면 클릭시 배경이 안 변함, 주석처리 하지않으면 클릭시 효과는 나오지만, item 위치가 변함
            }

        })
        viewModel.selectedDateSchedule.observe(viewLifecycleOwner, {
            it?.let {
                binding.detailSchoolScheduleRv.adapter = DetailScheduleAdapter(it,viewModel.currentMonth.value!!)
            }
        })

        viewModel.onClickTimeTableSwitch.observe(viewLifecycleOwner,{
            navigateFragment(R.id.action_global_timeTableFragment)
        })
    }
    private fun initView(){
        binding.schoolCalendarRv.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.schoolCalendarRv.adapter = SchoolCalendarAdapter(generateMonth(getCurrentDate()),viewModel)
        binding.detailSchoolScheduleRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.detailSchoolScheduleRv.adapter = DetailScheduleAdapter()
    }


}