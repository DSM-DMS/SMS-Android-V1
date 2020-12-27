package com.dms.sms.feature.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dms.sms.R
import com.dms.sms.feature.login.AutoLoginViewModel
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : Fragment() {

    private val viewModel : AutoLoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading_lottie.playAnimation()

        Handler(Looper.myLooper()!!).postDelayed({
            viewModel.autoLoginEvent.observe(viewLifecycleOwner, Observer {
                if (it)
                    findNavController().navigate(R.id.action_splashFragment_to_MainFragment)
                else
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)

            }) },3000)
    }


    override fun onStart() {
        super.onStart()


    }

    private fun startApp(){
        context?.let {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}