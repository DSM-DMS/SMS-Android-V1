package com.dms.sms

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

fun Fragment.navigateFragment(action : Int){
   findNavController().navigate(action)
}

fun Activity.navigateFragment(navHostId : Int,action : Int){
    findNavController(navHostId).navigate(action)
}

fun Activity.navigateFragmentWithArgs(navHostId : Int,action : NavDirections){
    findNavController(navHostId).navigate(action)
}