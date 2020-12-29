package com.dms.sms.feature.mypage

import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentLogOutDialogBinding
import com.dms.sms.feature.mypage.viewmodel.LogoutViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoutDialogFragment : BaseDialog<FragmentLogOutDialogBinding>() {
    override val viewModel: LogoutViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_log_out_dialog

    override fun observeEvent() {
        with(viewModel){
            logoutConfirm.observe(viewLifecycleOwner,{
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_logInFragment)
                dismiss()
            })
            logoutCancel.observe(viewLifecycleOwner,{ dismiss() })
        }
    }
}