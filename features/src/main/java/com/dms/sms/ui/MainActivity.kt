package com.dms.sms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dms.sms.R
import com.dms.sms.feature.login.DeleteLoginDataViewModel
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val outingApplyViewModel by viewModel<OutingApplyViewModel>()
    private val deleteLoginDataViewModel by viewModel<DeleteLoginDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deleteLoginDataViewModel.deleteLoginData()

    }


}