package com.dms.sms.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.dms.sms.BR
import com.dms.sms.R
import com.google.android.material.snackbar.Snackbar
import splitties.toast.toast

abstract class BaseFragment<T : ViewDataBinding> : Fragment(){

    lateinit var binding : T
    abstract val viewModel : BaseViewModel

    abstract val layoutId : Int

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.vm, viewModel)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(requireView()).popBackStack()
            }
        })

        viewModel.createSnackEvent.observe(viewLifecycleOwner, {
            Snackbar.make(view.rootView, it, Snackbar.LENGTH_SHORT).show()
        })
        viewModel.createToastEvent.observe(viewLifecycleOwner, {
            toast(it)
        })
        viewModel.backEvent.observe(this, {
            Navigation.findNavController(requireView()).popBackStack()
        })
        viewModel.expiredTokenEvent.observe(viewLifecycleOwner,{
            requireActivity().findNavController(R.id.fragment_container).navigate(R.id.action_mainFragment_to_logInFragment)
        })
        observeEvents()
    }

    abstract fun observeEvents()

}