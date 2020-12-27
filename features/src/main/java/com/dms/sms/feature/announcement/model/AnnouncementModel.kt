package com.dms.sms.feature.announcement.model

data class AnnouncementModel(
    val date: Int,
    val title: String,
    val content: String,
    val writerName: String,
    val nextTitle: String,
    val nextAnnouncementUUID: String,
    val previousTitle: String,
    val previousAnnouncementUUID: String,
)