package com.dms.sms.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dms.sms.feature.announcement.ui.AnnouncementsFragment
import com.dms.sms.feature.mypage.MyPageFragment
import com.dms.sms.feature.outing.OutingMainFragment
import com.dms.sms.feature.schedule.ui.ScheduleMainFragment

class MainPagerAdapter(fm : FragmentManager, lc : Lifecycle) : FragmentStateAdapter(fm, lc){

    override fun getItemCount(): Int =4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ScheduleMainFragment()
            1 -> OutingMainFragment()
            2 -> AnnouncementsFragment()
            else -> MyPageFragment()
        }
    }
}