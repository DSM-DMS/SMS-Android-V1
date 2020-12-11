package com.dms.sms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dms.sms.R
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val outingApplyViewModel by viewModel<OutingApplyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}