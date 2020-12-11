package com.dms.sms.outing.dialog

import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentOutingNoticeDialogBinding
import com.dms.sms.outing.viewmodel.OutingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OutingNoticeDialog : BaseDialog<FragmentOutingNoticeDialogBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_outing_notice_dialog

    override val viewModel by sharedViewModel<OutingApplyViewModel>()

    override fun observeEvent() {
        with(viewModel) {
            onSuccessDialogEvent.observe(this@OutingNoticeDialog, {
                outingWithDisease.value = !outingWithDisease.value!!
                dismiss()
            })
            onCancelEvent.observe(this@OutingNoticeDialog, {
                dismiss()
            })
        }
    }
}