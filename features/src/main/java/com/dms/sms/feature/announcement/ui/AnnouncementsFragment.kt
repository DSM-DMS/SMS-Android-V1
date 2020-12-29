package com.dms.sms.feature.announcement.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentAnnouncementsBinding
import com.dms.sms.feature.announcement.adapter.AnnouncementsAdapter
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel
import com.dms.sms.navigateFragment
import com.dms.sms.navigateFragmentWithArgs
import com.dms.sms.ui.MainFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel


class AnnouncementsFragment : BaseFragment<FragmentAnnouncementsBinding>() {

    override val viewModel: AnnouncementsViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_announcements

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun observeEvents() {
        viewModel.announcementEvent.observe(viewLifecycleOwner, {
            requireActivity().navigateFragmentWithArgs(R.id.fragment_container, MainFragmentDirections.actionMainFragmentToAnnouncementDetailFragment(it))
        })

    }

}