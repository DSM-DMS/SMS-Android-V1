package com.dms.sms

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

fun Fragment.navigateFragment(action : Int){
   findNavController().navigate(action)
}

fun Fragment.navigateFragmentWithHost(action : Int){
    childFragmentManager.primaryNavigationFragment!!.findNavController().navigate(action)
}

fun Activity.navigateFragment(navHostId : Int,action : Int){
    findNavController(navHostId).navigate(action)
}