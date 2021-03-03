package com.dms.sms.feature.outing.viewmodel

import com.dms.sms.base.BaseViewModel

class OutingNoticeViewModel : BaseViewModel(){

    fun clickBack() = backEvent.call()
}