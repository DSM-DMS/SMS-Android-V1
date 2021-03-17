package com.dms.sms.feature.mypage

import android.os.Bundle
import android.view.View
import com.dms.sms.R
import com.dms.sms.base.BackPressedBaseFragment
import com.dms.sms.base.EndPointBaseFragment
import com.dms.sms.databinding.FragmentMyPageBinding
import com.dms.sms.feature.mypage.viewmodel.MyPageViewModel
import com.dms.sms.navigateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyPageFragment : BackPressedBaseFragment<FragmentMyPageBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_my_page

    override val viewModel: MyPageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
    }
    override fun observeEvents() {
        with(viewModel){
            changePwEvent.observe(this@MyPageFragment, {
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_changePasswordFragment)
            })
            logoutEvent.observe(this@MyPageFragment,{
                val dialog = LogoutDialogFragment()
                dialog.show(requireActivity().supportFragmentManager, "LogOutDialogFragment")
            })
            developerEvent.observe(this@MyPageFragment,{
                requireActivity().navigateFragment(R.id.fragment_container,R.id.action_mainFragment_to_introducingDevelopersFragment)
            })
        }
    }

}