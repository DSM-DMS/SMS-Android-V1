package com.dms.data.dto.response

import com.dms.domain.announcement.entity.Announcement

data class AnnouncementResponseData(
    val date: Long,
    val title: String,
    val content: String,
    val writer_name: String,
    val next_title: String,
    val next_announcement_uuid: String,
    val previous_title: String,
    val previous_announcement_uuid: String,
)

fun AnnouncementResponseData.toEntity(): Announcement =
    Announcement(
        (this.date / 1000).toInt(),
        this.title,
        this.content,
        this.writer_name,
        this.next_title,
        this.next_announcement_uuid,
        this.previous_title,
        this.previous_announcement_uuid
    )