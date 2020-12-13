package com.dms.sms.feature.outing

import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingMainBinding
import com.dms.sms.navigateFragment
import com.dms.sms.feature.outing.viewmodel.OutingViewModel
import kotlinx.android.synthetic.main.fragment_outing_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingMainFragment : BaseFragment<FragmentOutingMainBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_outing_main

    override val viewModel: OutingViewModel by viewModel()

    override fun observeEvents() {
        with(viewModel){
            applyOutingEvent.observe(this@OutingMainFragment, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_outingApplyFragment)
            })
            outingHistoryEvent.observe(this@OutingMainFragment, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_outingHistoryFragment)
            })
            accessOutingEvent.observe(this@OutingMainFragment, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_outingAccessFragment)
            })
            noticeEvent.observe(this@OutingMainFragment, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_outingNoticeFragment)
            })
        }
    }

}