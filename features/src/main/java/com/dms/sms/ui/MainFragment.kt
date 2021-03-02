package com.dms.sms.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.base.BaseViewModel
import com.dms.sms.databinding.FragmentMainBinding
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

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
                binding.mainNavigation.menu.getItem(2).icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_unread)
            else
                binding.mainNavigation.menu.getItem(2).icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_notice)

        })
    }
}