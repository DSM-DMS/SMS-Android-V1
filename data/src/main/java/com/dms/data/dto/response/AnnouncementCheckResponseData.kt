package com.dms.data.dto.response

import com.dms.data.util.convertTimeToDate
import com.dms.domain.announcement.entity.Announcement
import com.dms.domain.announcement.entity.AnnouncementCheck

data class AnnouncementCheckResponseData(
    val club: Int,
    val school: Int
)

fun AnnouncementCheckResponseData.toEntity(): AnnouncementCheck =
    AnnouncementCheck(
        this.club,
        this.school
    )