package com.dms.sms.feature.outing

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleObserver
import com.dms.sms.R
import com.dms.sms.base.EndPointBaseFragment
import com.dms.sms.databinding.FragmentOutingMainBinding
import com.dms.sms.feature.outing.viewmodel.OutingViewModel
import com.dms.sms.navigateFragment
import kotlinx.android.synthetic.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingMainFragment : EndPointBaseFragment<FragmentOutingMainBinding>(), LifecycleObserver {

    override val layoutId: Int
        get() = R.layout.fragment_outing_main

    override val viewModel: OutingViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onCreate()
    }

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