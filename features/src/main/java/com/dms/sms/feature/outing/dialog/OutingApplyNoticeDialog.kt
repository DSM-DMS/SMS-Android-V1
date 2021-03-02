package com.dms.sms.feature.outing.dialog

import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentOutingApplyNoticeDialogBinding
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OutingApplyNoticeDialog : BaseDialog<FragmentOutingApplyNoticeDialogBinding>() {
    override val viewModel by sharedViewModel<OutingApplyViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_outing_apply_notice_dialog

    override fun observeEvent() {
        with(viewModel) {
            outingApplyNoticeConfirmEvent.observe(viewLifecycleOwner, {
                applyOuting()
                dismiss()
            })
            outingApplyNoticeCancelEvent.observe(viewLifecycleOwner, {
                dismiss()
            })
        }
    }
}