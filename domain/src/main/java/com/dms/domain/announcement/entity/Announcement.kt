package com.dms.domain.announcement.entity

data class Announcement(
    val date: Int, val title: String, val content: String,
    val writerName: String,
    val nextTitle: String,
    val nextAnnouncementUUID: String,
    val previousTitle: String,
    val previousAnnouncementUUID: String,
)