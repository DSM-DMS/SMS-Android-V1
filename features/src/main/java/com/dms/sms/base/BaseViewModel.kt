package com.dms.sms.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val createSnackEvent = SingleLiveEvent<String>()
    val createToastEvent = SingleLiveEvent<String>()
    val backEvent = SingleLiveEvent<Unit>()
    val expiredTokenEvent = SingleLiveEvent<Unit>()

}