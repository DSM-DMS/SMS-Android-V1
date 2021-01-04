package com.dms.domain.announcement.service

import com.dms.domain.announcement.entity.Announcement
import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.entity.SimpleAnnouncement
import com.dms.domain.announcement.repository.AnnouncementRepository
import com.dms.domain.base.Result
import com.dms.domain.base.toResult
import com.dms.domain.errorhandler.ErrorHandler
import io.reactivex.Single

class AnnouncementServiceImpl(private val announcementRepository: AnnouncementRepository, private val errorHandler: ErrorHandler) : AnnouncementService {
    override fun getAnnouncements(announcementPage : Int): Single<Result<Announcements>> =
        announcementRepository.getAnnouncements(announcementPage).toResult(errorHandler)

    override fun getAnnouncement(announcementUUID : String): Single<Result<Announcement>>  =
        announcementRepository.getAnnouncement(announcementUUID).toResult(errorHandler)

    override fun searchAnnouncements(searchQuery: String, announcementPage: Int): Single<Result<Announcements>> =
        announcementRepository.searchAnnouncements(searchQuery, announcementPage).toResult(errorHandler)

}