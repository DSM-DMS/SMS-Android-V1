package com.dms.sms.feature.mypage.viewmodel

import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent

class LogoutViewModel : BaseViewModel() {
    val logoutConfirm = SingleLiveEvent<Unit>()
    val logoutCancel = SingleLiveEvent<Unit>()

    fun clickConfirm() = logoutConfirm.call()
    fun clickCancel() = logoutCancel.call()
}