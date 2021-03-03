package com.dms.domain.announcement.service

import com.dms.domain.announcement.entity.Announcement
import com.dms.domain.announcement.entity.AnnouncementCheck
import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.entity.SimpleAnnouncement
import com.dms.domain.base.Result
import io.reactivex.Single

interface AnnouncementService {
    fun getAnnouncements(announcementPage : Int) : Single<Result<Announcements>>

    fun getAnnouncement(announcementUUID : String) : Single<Result<Announcement>>

    fun searchAnnouncements(searchQuery : String, announcementPage: Int) : Single<Result<Announcements>>

    fun checkAnnouncementsUnread(studentUUID : String) : Single<Result<AnnouncementCheck>>

}