package com.dms.sms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dms.sms.R
import com.dms.sms.navigateFragmentWithHost
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.schedule_fragment -> return@OnNavigationItemSelectedListener selectSchedule()
            R.id.outing_fragment ->return@OnNavigationItemSelectedListener selectOuting()
            R.id.notice_fragment -> return@OnNavigationItemSelectedListener selectNotice()
            R.id.mypage_fragment -> return@OnNavigationItemSelectedListener selectMyPage()
        }
        false
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

    }

    private fun selectSchedule() : Boolean{
        navigateFragmentWithHost(R.id.action_global_schoolScheduleFragment)
        return true

    }
    private fun selectOuting(): Boolean{
        navigateFragmentWithHost(R.id.action_global_outingMainFragment)
        return true
    }
    private fun selectNotice(): Boolean{
        navigateFragmentWithHost(R.id.action_global_noticeFragment)
        return true
    }
    private fun selectMyPage(): Boolean{
        navigateFragmentWithHost(R.id.action_global_myPageFragment)
        return true
    }
}