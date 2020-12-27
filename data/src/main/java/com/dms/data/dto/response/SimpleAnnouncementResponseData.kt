package com.dms.data.dto.response

import com.dms.data.util.convertTime
import com.dms.domain.announcement.entity.SimpleAnnouncement

data class SimpleAnnouncementResponseData(
    val announcement_uuid: String,
    val title: String,
    val number: Int,
    val date: Long,
    val views: Int, val writer_name: String, val is_checked: Int,
)

fun SimpleAnnouncementResponseData.toEntity(): SimpleAnnouncement =
    SimpleAnnouncement(
        this.announcement_uuid,
        this.title,
        this.number,
        (this.date).convertTime(),
        this.views,
        this.writer_name,
        this.is_checked,
    )