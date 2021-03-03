package com.dms.sms.feature.outing

import com.dms.sms.R
import com.dms.sms.base.EndPointBaseFragment
import com.dms.sms.databinding.FragmentOutingMainBinding
import com.dms.sms.feature.outing.viewmodel.OutingViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingMainFragment : EndPointBaseFragment<FragmentOutingMainBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_outing_main

    override val viewModel: OutingViewModel by viewModel()

    override fun observeEvents() {
        with(viewModel){
            applyOutingEvent.observe(viewLifecycleOwner, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_outingApplyFragment)
            })
            outingHistoryEvent.observe(viewLifecycleOwner, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_outingHistoryFragment)
            })
            accessOutingEvent.observe(viewLifecycleOwner, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_outingAccessFragment)
            })
            noticeEvent.observe(viewLifecycleOwner, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_outingNoticeFragment)
            })
        }
    }

}