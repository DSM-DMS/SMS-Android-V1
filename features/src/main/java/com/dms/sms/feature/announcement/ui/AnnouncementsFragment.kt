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
import org.koin.androidx.viewmodel.ext.android.viewModel


class AnnouncementsFragment : BaseFragment<FragmentAnnouncementsBinding>() {

    override val viewModel: AnnouncementsViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.fragment_announcements


    override fun observeEvents() {

    }

}