package com.dms.domain.announcement.repository

import com.dms.domain.announcement.entity.Announcement
import com.dms.domain.announcement.entity.AnnouncementCheck
import com.dms.domain.announcement.entity.Announcements
import io.reactivex.Single

interface AnnouncementRepository {
    fun getAnnouncements(announcementPage : Int) : Single<Announcements>

    fun getAnnouncement(announcementUUID : String) : Single<Announcement>

    fun searchAnnouncements(searchQuery : String, announcementPage : Int) : Single<Announcements>

    fun checkAnnouncementsUnread(studentUUID : String) : Single<AnnouncementCheck>
}