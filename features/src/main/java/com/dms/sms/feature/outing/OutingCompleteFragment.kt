package com.dms.sms.feature.outing

import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingCompleteBinding
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OutingCompleteFragment : BaseFragment<FragmentOutingCompleteBinding>() {
    override val viewModel: OutingApplyViewModel by sharedViewModel()

    override val layoutId: Int
        get() = R.layout.fragment_outing_complete

    override fun observeEvents() {
        with(viewModel){
            outingCompleteEvent.observe(viewLifecycleOwner, {
                navigateFragment(R.id.action_outingCompleteFragment_to_mainFragment)
            })
        }
    }

}