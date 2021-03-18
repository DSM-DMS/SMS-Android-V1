package com.dms.sms.feature.signup.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.base.BaseViewModel
import com.dms.sms.databinding.FragmentVerificationNumberBinding
import com.dms.sms.feature.signup.model.NoAccountStudentModel
import com.dms.sms.feature.signup.ui.dialog.QuestToDmsDialogFragment
import com.dms.sms.feature.signup.ui.dialog.WrongVerificationNumberDialogFragment
import com.dms.sms.feature.signup.viewmodel.SignUpViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class VerificationNumberFragment : BaseFragment<FragmentVerificationNumberBinding>() {
    override val viewModel: SignUpViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_verification_number


    override fun observeEvents() {
        viewModel.noAccountStudentEvent.observe(viewLifecycleOwner,
            {
                navigateFragment(R.id.action_verificationNumberFragment_to_signUpFragment)
            })
        viewModel.wrongVerificationNumberEvent.observe(viewLifecycleOwner, {
            WrongVerificationNumberDialogFragment().show(
                requireActivity().supportFragmentManager,
                "WrongVerificationNumberDialogFragment"
            )
        })
        viewModel.questToDmsEvent.observe(viewLifecycleOwner, {
            QuestToDmsDialogFragment().show(
                requireActivity().supportFragmentManager,
                "QuestToDmsDialogFragment"
            )
        })
    }
    override fun onResume() {
        super.onResume()
        Log.d("smsonre","dsd")
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.backEvent.call()
            }
        })
    }




}