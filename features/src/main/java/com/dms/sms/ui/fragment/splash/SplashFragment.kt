package com.dms.sms.ui.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.dms.sms.R
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading_lottie.playAnimation()
    }

    override fun onStart() {
        super.onStart()

        Handler().postDelayed({
            startApp()
        },3000)
    }

    private fun startApp(){
        context?.let {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}