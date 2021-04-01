package com.dms.sms.feature.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dms.sms.R
import com.dms.sms.base.BaseFragment
import com.dms.sms.databinding.FragmentSplashBinding
import com.dms.sms.feature.login.AutoLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadingLottie.playAnimation()

        Handler(Looper.myLooper()!!).postDelayed({
            viewModel.autoLoginEvent.observe(viewLifecycleOwner, {
                if (it)
                    findNavController().navigate(R.id.action_splashFragment_to_MainFragment)
                else
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

            })
        }, 1500)
    }

    override val layoutId: Int
        get() = R.layout.fragment_splash

    override fun observeEvents() {

    }

    override val viewModel: AutoLoginViewModel by viewModel()
}