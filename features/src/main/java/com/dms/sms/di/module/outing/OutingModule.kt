package com.dms.sms.di.module.outing

import com.dms.sms.viewmodel.outing.OutingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val outingModule: Module = module {
    viewModel { OutingViewModel() }
}