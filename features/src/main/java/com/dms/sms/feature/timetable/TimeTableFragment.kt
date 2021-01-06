package com.dms.sms.feature.timetable

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentTimeTableBinding
import com.dms.sms.feature.timetable.adapter.TimeTableAdapter
import com.dms.sms.feature.timetable.viewmodel.TimeTableViewModel
import com.dms.sms.navigateFragment
import kotlinx.android.synthetic.main.fragment_school_schedule.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TimeTableFragment : BaseFragment<FragmentTimeTableBinding>() {

    override val viewModel: TimeTableViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_time_table

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.timeTableRecyclerView.layoutManager = GridLayoutManager(context,7,RecyclerView.HORIZONTAL, false)
        binding.timeTableRecyclerView.adapter = TimeTableAdapter()
    }

    override fun observeEvents() {
        calendar_switch_btn.setOnClickListener {
            navigateFragment(R.id.action_global_schoolScheduleFragment)
        }
    }

}