package com.dms.sms.feature.outing.model

import com.dms.domain.outing.response.DetailOutingResponse
import com.dms.domain.outing.response.OutingList
import java.text.SimpleDateFormat
import java.util.*

data class DetailOutingModel(
    val place: String,
    val startTime: String,
    val endTime: String,
    val outingStatus: String,
    val name: String,
    val _grade: Int,
    val _group: Int,
    val _number: Int,
    val profileUri: String
){
    val studentNumber: String
        get() = "$_grade$_group$_number"

    val outingDate: String
        get() {
            val sd = Date(startTime.toLong() * 1000)
            return SimpleDateFormat("yyyy.MM.dd",Locale.KOREA).format(sd)
        }

    val outingTime: String
        get() {
            val sd = Date(startTime.toLong() * 1000)
            val simpleDateFormat = SimpleDateFormat("HH:mm",Locale.KOREA)
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

            return simpleDateFormat.format(sd)
        }
}

fun DetailOutingResponse.toModel(): DetailOutingModel =
    DetailOutingModel(
        this.place,
        this.startTime,
        this.endTime,
        this.outingStatus,
        this.name,
        this.grade,
        this.group,
        this.number,
        this.profileUri
    )