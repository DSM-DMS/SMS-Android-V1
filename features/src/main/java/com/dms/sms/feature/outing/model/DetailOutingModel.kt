package com.dms.sms.feature.outing.model

import com.dms.domain.outing.response.DetailOutingResponse
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
) {
    val studentNumber: String
        get() = "$_grade$_group$_number"

    val outingDate: String
        get() {
            val sd = Date(startTime.toLong() * 1000)
            val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

            return simpleDateFormat.format(sd)
        }

    val outingTime: String
        get() {
            val sd = Date(startTime.toLong() * 1000)
            val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.KOREA)
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

            return simpleDateFormat.format(sd)
        }

    val status: String
        get() {
            var outingState = ""
            when(outingStatus){
                "0" -> outingState = "승인 대기"
                "1" -> outingState = "학부모 승인"
                "-1" -> outingState = "학부모 거절"
                "2" -> outingState = "선생님 승인"
                "-2" -> outingState = "선생님 거절"
                "3" -> outingState = "외출 시작"
                "4" -> outingState = "외출 종료"
                "5" -> outingState = "외출 인증 승인"
            }

            return outingState
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