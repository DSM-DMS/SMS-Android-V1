package com.dms.sms.feature.login

import com.dms.sms.R
import com.dms.sms.databinding.FragmentLoginBinding
import com.dms.sms.base.BaseFragment
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.toast.toast


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_login


    override val viewModel: LoginViewModel by viewModel()

    override fun observeEvents() {
        viewModel.loginSuccessEvent.observe(viewLifecycleOwner, {
            navigateFragment(R.id.action_loginFragment_to_MainFragment)

        })
        viewModel.createToastEvent.observe(viewLifecycleOwner, {
            toast(it)
        })



    }



}