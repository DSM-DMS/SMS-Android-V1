package com.dms.sms.feature.outing.viewmodel

import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent

class OutingViewModel : BaseViewModel() {

    val applyOutingEvent = SingleLiveEvent<Unit>()
    val outingHistoryEvent = SingleLiveEvent<Unit>()
    val accessOutingEvent = SingleLiveEvent<Unit>()
    val noticeEvent = SingleLiveEvent<Unit>()


    fun clickApplyOuting() = applyOutingEvent.call()
    fun clickOutingHistory() = outingHistoryEvent.call()
    fun clickAccessOuting() = accessOutingEvent.call()
    fun clickNoticeEvent() = noticeEvent.call()

}