package com.dms.sms.feature.mypage

import android.util.Log
import androidx.activity.OnBackPressedCallback
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
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
                binding.newPasswordEtLayout.error = null
            })
            currentPwErrorEvent.observe(viewLifecycleOwner, {
                binding.currentPasswordEtLayout.error = "비밀번호가 일치하지 않습니다."
                binding.newPasswordConfirmEtLayout.error = null
                binding.newPasswordEtLayout.error = null
            })
            successChangePw.observe(viewLifecycleOwner, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_changePasswordFragment_to_loginFragment)
            })
            pwFormCheckEvent.observe(viewLifecycleOwner, {
                binding.newPasswordEtLayout.error = "비밀번호는 4자이상으로 입력해주세요"
                binding.newPasswordConfirmEtLayout.error = null
                binding.currentPasswordEtLayout.error = null
            })
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("smsonre","dsd")
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.clickBack()
            }
        })
    }

}