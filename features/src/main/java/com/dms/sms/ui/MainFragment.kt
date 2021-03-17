package com.dms.sms.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.dms.sms.R
import com.dms.sms.base.BackPressedBaseFragment
import com.dms.sms.base.BaseFragment
import com.dms.sms.base.BaseViewModel
import com.dms.sms.databinding.FragmentMainBinding
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BackPressedBaseFragment<FragmentMainBinding>() {

    override val viewModel: AnnouncementsViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkAnnouncementsUnread()
        binding.mainVp.adapter= MainPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.mainVp.registerOnPageChangeCallback(PageChangeCallback())
        binding.mainNavigation.itemIconTintList =null
        binding.mainNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.schedule_fragment ->{
                    binding.mainVp.currentItem = 0
                }
                R.id.outing_fragment ->{
                    binding.mainVp.currentItem = 1
                }
                R.id.notice_fragment ->{
                    binding.mainVp.currentItem = 2
                }
                R.id.mypage_fragment ->{
                    binding.mainVp.currentItem = 3
                }

            }
            true
        }
    }
    private inner class PageChangeCallback: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.mainNavigation.selectedItemId = when (position) {
                0 -> R.id.schedule_fragment
                1 -> R.id.outing_fragment
                2 -> R.id.notice_fragment
                3 -> R.id.mypage_fragment
                else -> error("no such position: $position")
            }
        }
    }



    override fun observeEvents() {
        viewModel.announcementsChecked.observe(viewLifecycleOwner,{
            if(it!=0)
                binding.mainNavigation.menu.getItem(2).icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_unread)
            else
                binding.mainNavigation.menu.getItem(2).icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_notice)

        })
    }
}
