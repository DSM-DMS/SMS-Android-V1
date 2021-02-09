package com.dms.sms.feature.schedule.model

data class Day(
    val date: String,
    val schedule: MutableList<ScheduleModel> = mutableListOf(),
    var isSelected : Boolean = false
)