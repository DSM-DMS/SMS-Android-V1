package com.dms.domain.announcement.entity

data class SimpleAnnouncement(val announcementUUID : String,
                              val title : String,
                              val number : Int,
                              val date : String,
                              val views : Int,
                              val writerName : String,
                              val isChecked : Int)
