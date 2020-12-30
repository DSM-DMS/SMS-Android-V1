package com.dms.sms.feature.mypage

import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.base.BaseViewModel
import com.dms.sms.databinding.FragmentChangePasswordBinding
import com.dms.sms.feature.mypage.viewmodel.ChangePwViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>() {

    override val viewModel: ChangePwViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_change_password

    override fun observeEvents() {
        with(viewModel){
            confirmPwErrorEvent.observe(viewLifecycleOwner, {
                binding.newPasswordConfirmEtLayout.error = "비밀번호가 일치하지 않습니다."
                binding.currentPasswordEtLayout.error = null
            })
            currentPwErrorEvent.observe(viewLifecycleOwner, {
                binding.currentPasswordEtLayout.error = "비밀번호가 일치하지 않습니다."
                binding.newPasswordConfirmEtLayout.error = null
            })
            successChangePw.observe(viewLifecycleOwner, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_changePasswordFragment_to_loginFragment)
            })
        }
    }

}