package com.dms.data.repository

import com.dms.data.datasource.AnnouncementDataSource
import com.dms.data.dto.response.toEntity
import com.dms.domain.announcement.entity.Announcement
import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.entity.SimpleAnnouncement
import com.dms.domain.announcement.repository.AnnouncementRepository
import io.reactivex.Single

class AnnouncementRepositoryImpl(private val announcementDataSource: AnnouncementDataSource) : AnnouncementRepository{
    override fun getAnnouncements(announcementPage : Int): Single<Announcements> =
        announcementDataSource.getAnnouncements(announcementPage).map { it.toEntity() }

    override fun getAnnouncement(announcementUUID: String): Single<Announcement> =
        announcementDataSource.getAnnouncement(announcementUUID).map {
            it.toEntity()
        }

    override fun searchAnnouncements(searchQuery: String, announcementPage : Int): Single<Announcements> =
        announcementDataSource.searchAnnouncements(searchQuery, announcementPage).map {
            it.toEntity()
        }
}