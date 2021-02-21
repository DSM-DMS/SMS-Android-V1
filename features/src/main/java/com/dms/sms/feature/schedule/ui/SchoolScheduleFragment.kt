package com.dms.sms.feature.schedule.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentSchoolScheduleBinding
import com.dms.sms.feature.schedule.adapter.CViewPagerAdapter
import com.dms.sms.feature.schedule.adapter.DetailScheduleAdapter
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SchoolScheduleFragment : BaseFragment<FragmentSchoolScheduleBinding>() {
    override val viewModel : SchoolScheduleViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_school_schedule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun observeEvents() {

        viewModel.selectedDateSchedule.observe(viewLifecycleOwner, {
            it?.let {
                (binding.detailSchoolScheduleRv.adapter as DetailScheduleAdapter).updateDetailSchedule(it, viewModel.currentMonth.value!!)
            }
        })
        viewModel.onClickTimeTableSwitch.observe(viewLifecycleOwner,{
            navigateFragment(R.id.action_global_timeTableFragment)
        })

    }
    private fun initView(){
        viewModel.onCreate()
        binding.calendarVp.adapter = CViewPagerAdapter(viewModel)
        binding.detailSchoolScheduleRv.layoutManager = LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
        binding.detailSchoolScheduleRv.adapter = DetailScheduleAdapter()

        Log.d("currentItem" ,binding.calendarVp.currentItem.toString())
        binding.calendarVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                viewModel.onScroll()
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                if(position< viewModel.currentPagePosition.value!!){
                    viewModel.onClickPrevious()
                }
                else if(position> viewModel.currentPagePosition.value!!){
                    viewModel.onClickNext()
                }
            }

        })
    }



}