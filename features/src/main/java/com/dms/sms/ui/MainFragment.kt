package com.dms.sms.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.dms.sms.R
import com.dms.sms.base.BackPressedBaseFragment
import com.dms.sms.databinding.FragmentMainBinding
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BackPressedBaseFragment<FragmentMainBinding>() {

    override val viewModel: AnnouncementsViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkAnnouncementsUnread()
        binding.mainNavigation.setupWithNavController(Navigation.findNavController(requireActivity(), R.id.main_container))
        binding.mainNavigation.itemIconTintList =null
        }

    override fun observeEvents() {
        viewModel.announcementsChecked.observe(viewLifecycleOwner,{
            if(it!=0)
                binding.mainNavigation.menu.getItem(2).icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_notice_unread_background)
            else
                binding.mainNavigation.menu.getItem(2).icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_notice_background)
        })
    }
}
