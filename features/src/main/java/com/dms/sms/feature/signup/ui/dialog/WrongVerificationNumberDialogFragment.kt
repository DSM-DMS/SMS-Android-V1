package com.dms.sms.feature.signup.ui.dialog

import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.ValidationFailedDialogBinding
import com.dms.sms.feature.signup.viewmodel.SignUpViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class WrongVerificationNumberDialogFragment : BaseDialog<ValidationFailedDialogBinding>() {


    override val viewModel: SignUpViewModel by viewModel()

    override val layoutId: Int = R.layout.validation_failed_dialog

    override fun observeEvent() {
        viewModel.cancelEvent.observe(viewLifecycleOwner, {
            dismiss()
        })
        viewModel.backEvent.observe(viewLifecycleOwner,{
            navigateFragment(R.id.action_verificationNumberFragment_to_loginFragment)
        })
    }

}