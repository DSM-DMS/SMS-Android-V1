package com.dms.sms.feature.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentIntroducingDevelopersBinding
import com.dms.sms.feature.mypage.adapter.DeveloperAdapter
import com.dms.sms.feature.mypage.viewmodel.IntroduceDeveloperViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class IntroducingDevelopersFragment : BaseFragment<FragmentIntroducingDevelopersBinding>() {
    override val viewModel: IntroduceDeveloperViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_introducing_developers

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.introducingDevelopersRv.layoutManager = GridLayoutManager(context,2)
        binding.introducingDevelopersRv.adapter = DeveloperAdapter()
    }

    override fun observeEvents() {

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