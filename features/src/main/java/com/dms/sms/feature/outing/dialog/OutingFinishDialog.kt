package com.dms.sms.feature.outing.dialog

import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.FragmentOutingFinishDialogBinding
import com.dms.sms.feature.outing.viewmodel.OutingAccessViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OutingFinishDialog : BaseDialog<FragmentOutingFinishDialogBinding>() {
    override val viewModel: OutingAccessViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_outing_finish_dialog

    override fun observeEvent() {
        with(viewModel) {
            outingFinishConfirmEvent.observe(viewLifecycleOwner, {
                dismiss()
                startOrFinishOuting()
            })

            outingFinishCancelEvent.observe(viewLifecycleOwner, {
                dismiss()
            })
        }

    }

}