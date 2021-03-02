package com.dms.sms.feature.login

import com.dms.sms.R
import com.dms.sms.base.EndPointBaseFragment
import com.dms.sms.databinding.FragmentLoginBinding
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.toast.toast


class LoginFragment : EndPointBaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModel()

    override fun observeEvents() {
        viewModel.loginSuccessEvent.observe(viewLifecycleOwner, {
            navigateFragment(R.id.action_loginFragment_to_MainFragment)

        })
        viewModel.onClickSignUpEvent.observe(viewLifecycleOwner, {
            navigateFragment(R.id.action_loginFragment_to_verificationNumberFragment)
        })
        viewModel.loginErrorEvent.observe(viewLifecycleOwner, {
            binding.passwordEtLayout.error = null
            binding.passwordEtLayout.error = "아이디 또는 비밀번호가 일치하지 않습니다"
        })
        viewModel.createToastEvent.observe(viewLifecycleOwner, {
            toast(it)
        })



    }



}