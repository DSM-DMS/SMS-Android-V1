package com.dms.domain.announcement.usecase

import com.dms.domain.announcement.entity.AnnouncementCheck
import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.entity.SimpleAnnouncement
import com.dms.domain.announcement.service.AnnouncementService
import com.dms.domain.base.UseCase
import com.dms.domain.base.Result
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class CheckAnnouncementUnreadUseCase(private val announcementService: AnnouncementService, disposable: CompositeDisposable) : UseCase<String, Result<AnnouncementCheck>>(disposable) {
    override fun buildUseCase(data: String): Single<Result<AnnouncementCheck>> =
        announcementService.checkAnnouncementsUnread(data)
}