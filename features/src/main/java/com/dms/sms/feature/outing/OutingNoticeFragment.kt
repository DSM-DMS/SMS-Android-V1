package com.dms.sms.feature.outing

import androidx.activity.OnBackPressedCallback
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentOutingNoticeBinding
import com.dms.sms.feature.outing.viewmodel.OutingNoticeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingNoticeFragment : BaseFragment<FragmentOutingNoticeBinding>() {
    override val viewModel: OutingNoticeViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_outing_notice

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.clickBack()
            }
        })
    }
    override fun observeEvents() {

    }

}