package com.dms.domain.schedule.entity

data class ScheduleDate(val year: Int, val month: Int)

fun ScheduleDate.calculateDate(): ScheduleDate {
    return when {
        month > 12 -> {
            ScheduleDate(year + 1, 1)
        }
        month < 1 -> {
            ScheduleDate(year - 1, 12)

        }
        else -> {
            ScheduleDate(year, month)

        }
    }
}
