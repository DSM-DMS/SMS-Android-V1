package com.dms.sms.feature.signup.ui.dialog

import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.ValidationFailedDialogBinding
import com.dms.sms.feature.signup.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestToDmsDialogFragment : BaseDialog<ValidationFailedDialogBinding>() {

    override val viewModel: SignUpViewModel by viewModel()

    override val layoutId: Int = R.layout.quest_to_dms_dialog

    override fun observeEvent() {
        viewModel.cancelEvent.observe(viewLifecycleOwner, {
            dismiss()
        })
    }
}