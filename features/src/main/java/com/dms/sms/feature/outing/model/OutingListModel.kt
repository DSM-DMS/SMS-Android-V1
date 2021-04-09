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
    val outingStatus: String,
    val arrivalTime: Int
) {
    val isEmergency: Boolean
        get() {
            return outingSituation == "EMERGENCY"
        }

    val todayOutingStatus: String
        get() {
            return if (outingStatus == "0" || outingStatus == "-2" || outingStatus == "-1" || outingStatus == "1" || outingStatus == "2") {
                if (!isToday(startTime.toLong())) {
                    "6"
                } else {
                    outingStatus
                }
            } else {
                outingStatus
            }
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
        this.arrivalTime
    )