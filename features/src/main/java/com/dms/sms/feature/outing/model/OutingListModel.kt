package com.dms.sms.feature.outing.model

import com.dms.domain.outing.response.OutingList
import com.dms.domain.util.isToday

data class OutingModel(
    val outingUUID: String,
    val place: String,
    val reason: String,
    val startTime: Int,
    val endTime: Int,
    val outingSituation: String,
    val outingStatus: String
) {
    val isEmergency: Boolean
        get() {
            return outingSituation == "EMERGENCY"
        }

    val todayOutingStatus: String
        get() {
            println(isToday(startTime.toLong()))
            return outingStatus
        }
}

fun OutingList.toModel(): OutingModel =
    OutingModel(
        this.outingUUID,
        this.place,
        this.reason,
        this.startTime,
        this.endTime,
        this.outingSituation,
        this.outingStatus,
    )