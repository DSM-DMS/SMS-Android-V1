package com.dms.sms.feature.signup.ui.dialog

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import com.dms.sms.R
import com.dms.sms.base.BaseDialog
import com.dms.sms.databinding.ValidationFailedDialogBinding
import com.dms.sms.feature.signup.viewmodel.SignUpViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreatedAccountDialogFragment : BaseDialog<ValidationFailedDialogBinding>() {
    override val viewModel: SignUpViewModel by viewModel()
    override val layoutId: Int = R.layout.account_created_dialog
    override fun observeEvent() {
        viewModel.cancelEvent.observe(viewLifecycleOwner, {
            dismiss()
            navigateFragment(R.id.action_signUpFragment_to_loginFragment)
        })

    }

}