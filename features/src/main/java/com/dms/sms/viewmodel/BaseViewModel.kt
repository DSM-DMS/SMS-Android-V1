package com.dms.sms.viewmodel

import androidx.lifecycle.ViewModel
import com.dms.sms.base.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val createToastEvent = SingleLiveEvent<String>()

}