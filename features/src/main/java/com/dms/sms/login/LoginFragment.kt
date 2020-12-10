package com.dms.sms.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dms.sms.R
import com.dms.sms.databinding.FragmentLoginBinding
import com.dms.sms.base.BaseFragment
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_login


    override val viewModel: LoginViewModel by viewModel()

    override fun observeEvents() {
        viewModel.loginSuccessEvent.observe(viewLifecycleOwner, {
            navigateFragment(R.id.action_loginFragment_to_MainFragment)
        })



    }



}