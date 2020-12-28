package com.dms.sms.feature.mypage

import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.base.BaseViewModel
import com.dms.sms.databinding.FragmentMyPageBinding
import com.dms.sms.feature.mypage.viewmodel.MyPageViewModel
import com.dms.sms.feature.outing.viewmodel.OutingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MyPageFragment : BaseFragment<FragmentMyPageBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_my_page

    override val viewModel: MyPageViewModel by viewModel()

    override fun observeEvents() {

    }

}