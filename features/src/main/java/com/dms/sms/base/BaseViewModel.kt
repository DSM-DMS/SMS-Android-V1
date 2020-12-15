package com.dms.sms.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val createToastEvent = SingleLiveEvent<String>()
}