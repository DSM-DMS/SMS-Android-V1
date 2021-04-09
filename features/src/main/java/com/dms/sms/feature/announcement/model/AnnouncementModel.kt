package com.dms.sms.feature.announcement.model

import com.dms.domain.announcement.entity.Announcement

data class AnnouncementModel(
    val date: String,
    val title: String,
    val content: String,
    val writerName: String,
    val nextTitle: String,
    val nextAnnouncementUUID: String,
    val previousTitle: String,
    val previousAnnouncementUUID: String,
)

fun Announcement.toModel() =
    AnnouncementModel(
        this.date,
        this.title,
        this.content,
        this.writerName,
        this.nextTitle,
        this.nextAnnouncementUUID,
        this.previousTitle,
        this.previousAnnouncementUUID
    )