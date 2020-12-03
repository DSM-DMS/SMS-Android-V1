package com.dms.sms.ui.fragment.schedule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.dms.sms.R
import com.dms.sms.navigateFragment
import com.dms.sms.ui.calendar.CalendarPagerAdapter
import com.dms.sms.ui.calendar.CalendarTime

import com.dms.sms.ui.calendar.DetailScheduleAdapter
import com.dms.sms.ui.calendar.calculateTime
import kotlinx.android.synthetic.main.calender_view.view.*
import kotlinx.android.synthetic.main.fragment_school_schedule.*
import kotlinx.android.synthetic.main.fragment_school_schedule.view.*
import kotlinx.android.synthetic.main.item_detail_school_schedule.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


class SchoolScheduleFragment : Fragment() {

    private var month = 0
    private var year = 0
    lateinit var adapter: CalendarPagerAdapter
    private val calendarList = listOf<CalendarTime>(CalendarTime(2020,8), CalendarTime(2020,9),CalendarTime(2020,10)
            ,CalendarTime(2020,11),CalendarTime(2020,12),CalendarTime(2021,1)
            ,CalendarTime(2021,2),CalendarTime(2021,3))
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_school_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView(Date())
        calendar_switch_btn.setOnClickListener{
            navigateFragment(R.id.action_global_timeTableFragment)
        }
    }
    private fun setView(date: Date){

        year = SimpleDateFormat("yyyy",Locale.KOREA).format(date).toInt()
        month = SimpleDateFormat("M",Locale.KOREA).format(date).toInt()
        school_schedule_calender_title_tv.text ="${year}년 ${month}월"


        adapter = CalendarPagerAdapter(calendarList,year, month, detail_school_schedule_rv)
        calendar_pager.adapter = adapter
        calendar_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        calendar_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                school_schedule_calender_title_tv.text ="${adapter.getYear()}년 ${adapter.getMonth()}월"
                super.onPageSelected(position)
            }


        })
        previous_month_img.setOnClickListener {
            val (year, month) = calculateTime(this.year, this.month-1)
            this.year = year
            this.month = month
            adapter.setDate(this.year, this.month)
            calendar_pager.post {
                calendar_pager.setCurrentItem(calendar_pager.currentItem-1,true)
            }
        }

        next_month_img.setOnClickListener {
            val (year, month) = calculateTime(this.year, this.month+1)
            this.year = year
            this.month = month
            adapter.setDate(this.year, this.month)
            calendar_pager.post {
                calendar_pager.setCurrentItem(calendar_pager.currentItem+1,true)
            }
        }


    }

}