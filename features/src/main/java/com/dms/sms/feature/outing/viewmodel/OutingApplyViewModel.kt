package com.dms.sms.feature.outing.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dms.sms.base.BaseViewModel
import com.dms.sms.base.SingleLiveEvent
import java.util.*

class OutingApplyViewModel : BaseViewModel() {

    var applyDate: String ?= null
    var startTime: String ?= null
    var endTime: String ?= null
    val calendar = Calendar.getInstance(Locale.KOREA)

    val outingWithDisease = MutableLiveData(true)
    val outingDate = MutableLiveData<String>()
    val outingStartTime = MutableLiveData<String>()
    val outingEndTime = MutableLiveData<String>()

    val onNextEvent = SingleLiveEvent<Unit>()
    val onDiseaseCancelEvent = SingleLiveEvent<Unit>()
    val onCancelEvent = SingleLiveEvent<Unit>()
    val onSuccessDialogEvent = SingleLiveEvent<Unit>()
    val diseaseEvent = SingleLiveEvent<Unit>()
    val dateEvent = SingleLiveEvent<Unit>()
    val startTimeEvent = SingleLiveEvent<Unit>()
    val endTimeEvent = SingleLiveEvent<Unit>()

    fun clickDisease() = diseaseEvent.call()
    fun clickDate() = dateEvent.call()
    fun clickStartTime() = startTimeEvent.call()
    fun clickEndTime() = endTimeEvent.call()
    fun clickSuccess() = onSuccessDialogEvent.call()
    fun clickCancel() = onCancelEvent.call()
    fun clickDiseaseCancel() = onDiseaseCancelEvent.call()
}