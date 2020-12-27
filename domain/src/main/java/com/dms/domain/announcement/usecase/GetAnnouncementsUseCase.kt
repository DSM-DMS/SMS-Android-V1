package com.dms.domain.announcement.usecase

import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.entity.SimpleAnnouncement
import com.dms.domain.announcement.service.AnnouncementService
import com.dms.domain.base.UseCase
import com.dms.domain.base.Result
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetAnnouncementsUseCase(private val announcementService: AnnouncementService, disposable: CompositeDisposable) : UseCase<Int, Result<Announcements>>(disposable) {
    override fun buildUseCase(data: Int): Single<Result<Announcements>> =
        announcementService.getAnnouncements(data)
}