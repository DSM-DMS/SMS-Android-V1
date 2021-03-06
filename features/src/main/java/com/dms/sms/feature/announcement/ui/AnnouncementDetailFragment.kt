package com.dms.sms.feature.announcement.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentAnnouncementDetailBinding
import com.dms.sms.feature.announcement.viewmodel.AnnouncementDetailViewModel
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import work.upstarts.editorjskit.models.HeadingLevel
import work.upstarts.editorjskit.ui.EditorJsAdapter
import work.upstarts.editorjskit.ui.theme.EJStyle


class AnnouncementDetailFragment : BaseFragment<FragmentAnnouncementDetailBinding>(){

    override val viewModel: AnnouncementDetailViewModel by viewModel()
    private val announcementsViewModel : AnnouncementsViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_announcement_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(requireContext())
        lifecycle.addObserver(viewModel)
    }

    override fun observeEvents() {
        viewModel.backButtonEvent.observe(viewLifecycleOwner, {
            viewModel.backEvent.call()
        })
        viewModel.announcements.observe(viewLifecycleOwner,{
            announcementsViewModel.setAnnouncements(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onCreate(announcementsViewModel.announcementEvent.value!!, announcementsViewModel.currentPage.value!!)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.backEvent.call()
            }
        })
    }

    private fun initView(context: Context){
        binding.announcementDetailRv.adapter =  EditorJsAdapter(
            EJStyle.builderWithDefaults(context.applicationContext)
                .paragraphTextColor(ContextCompat.getColor(context, R.color.colorBlack))

                .linkColor(ContextCompat.getColor(context, R.color.link_color))
                .headingMargin(
                    STANDARD_MARGIN,
                    ZERO_MARGIN,
                    ZERO_MARGIN,
                    ZERO_MARGIN,
                    HeadingLevel.h1
                )
                .headingMargin(
                    STANDARD_MARGIN,
                    ZERO_MARGIN,
                    ZERO_MARGIN,
                    ZERO_MARGIN,
                    HeadingLevel.h2
                )
                .listTextItemTextSize(18f)
                .dividerBreakHeight(DIVIDER_HEIGHT)
                .dividerBreakHeight(DIVIDER_HEIGHT)
                .build()
        )
    }

}

const val ZERO_MARGIN = 0
const val STANDARD_MARGIN = 16
const val DIVIDER_HEIGHT = 3
