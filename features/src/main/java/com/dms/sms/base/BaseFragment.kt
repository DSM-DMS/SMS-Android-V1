package com.dms.sms.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dms.sms.BR
import com.google.android.material.snackbar.Snackbar

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

        viewModel.createToastEvent.observe(viewLifecycleOwner, {
            Snackbar.make(view.rootView, it, Snackbar.LENGTH_SHORT).show()
        })
        viewModel.backEvent.observe(this, {
            Navigation.findNavController(requireView()).popBackStack()
        })

        observeEvents()
    }

    abstract fun observeEvents()

}