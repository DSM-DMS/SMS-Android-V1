package com.dms.sms.ui

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            val attrib = window.attributes
            attrib.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        deleteLoginDataViewModel.deleteLoginData()

    }


}