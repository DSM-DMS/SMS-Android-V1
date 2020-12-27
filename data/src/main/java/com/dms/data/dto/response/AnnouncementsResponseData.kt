package com.dms.data.dto.response

import com.dms.domain.announcement.entity.Announcements


data class AnnouncementsResponseData(val announcements : List<SimpleAnnouncementResponseData>, val size : Int)

fun AnnouncementsResponseData.toEntity(): Announcements =
    Announcements(
        this.announcements.map { it.toEntity() },
        this.size
    )
