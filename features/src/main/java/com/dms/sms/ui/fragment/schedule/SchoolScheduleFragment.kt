package com.dms.sms.ui.fragment.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dms.sms.R
import com.dms.sms.navigateFragment

import com.dms.sms.ui.calendar.DetailScheduleAdapter
import kotlinx.android.synthetic.main.calender_view.view.*
import kotlinx.android.synthetic.main.fragment_school_schedule.*
import kotlinx.android.synthetic.main.fragment_school_schedule.view.*
import java.util.*


class SchoolScheduleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        school_schedule_calender.setCalender(Date())

        calendar_switch_btn.setOnClickListener {
            navigateFragment(R.id.action_global_timeTableFragment)
        }
    }

}