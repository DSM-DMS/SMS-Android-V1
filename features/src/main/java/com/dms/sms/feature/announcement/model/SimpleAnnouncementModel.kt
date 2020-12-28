package com.dms.sms.feature.announcement.model

import com.dms.domain.announcement.entity.SimpleAnnouncement

data class SimpleAnnouncementModel(
    val announcementUUID: String,
    val title: String,
    val number: String,
    val date: String,
    val views: String,
    val writerName: String,
    val isChecked: Int
)

fun SimpleAnnouncement.toModel(): SimpleAnnouncementModel =
    SimpleAnnouncementModel(
        this.announcementUUID,
        this.title,
        this.number.toString(),
        this.date,
        this.views.toString(),
        this.writerName,
        this.isChecked
    )