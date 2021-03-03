package com.dms.sms.feature.outing.dialog

import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentOutingStartDialogBinding
import com.dms.sms.feature.outing.viewmodel.OutingAccessViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OutingStartDialog : BaseDialog<FragmentOutingStartDialogBinding>() {
    override val viewModel by sharedViewModel<OutingAccessViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_outing_start_dialog

    override fun observeEvent() {
        with(viewModel) {
            outingStartConfirmEvent.observe(viewLifecycleOwner, {
                startOrFinishOuting()
                getStudentUUID()
                dismiss()
            })

            outingStartCancelEvent.observe(viewLifecycleOwner, {
                dismiss()
            })
        }
    }

}