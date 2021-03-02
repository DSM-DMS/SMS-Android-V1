package com.dms.domain.schedule.entity

data class Schedule(val scheduleUUID : String,val startMonth : Int, val startDay : Int, val endMonth : Int, val endDay : Int, val detail : String)
