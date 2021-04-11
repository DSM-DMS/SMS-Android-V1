package com.dms.sms.feature.signup.ui

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentSignUpBinding
import com.dms.sms.feature.signup.ui.dialog.CreatedAccountDialogFragment
import com.dms.sms.feature.signup.ui.dialog.QuestToDmsDialogFragment
import com.dms.sms.feature.signup.ui.dialog.SameIdExistenceDialogFragment
import com.dms.sms.feature.signup.viewmodel.SignUpViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    override val viewModel: SignUpViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_sign_up

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun observeEvents() {
        viewModel.questToDmsEvent.observe(viewLifecycleOwner, {
            QuestToDmsDialogFragment().show(requireActivity().supportFragmentManager,"QuestToDmsDialogFragment")
        })
        viewModel.signUpSuccessEvent.observe(viewLifecycleOwner, {
            CreatedAccountDialogFragment().show(requireActivity().supportFragmentManager, "CreatedAccountDialogFragment")
        })
        viewModel.duplicateIdEvent.observe(viewLifecycleOwner, {
            SameIdExistenceDialogFragment().show(requireActivity().supportFragmentManager, "SameIdExistenceDialogFragment")
        })
        viewModel.loginFailedEvent.observe(viewLifecycleOwner, {
            navigateFragment(R.id.action_signUpFragment_to_loginFragment)
        })
    }

    private fun initView(){
        binding.studentPhoneNumberEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.backEvent.call()
            }
        })
    }
}