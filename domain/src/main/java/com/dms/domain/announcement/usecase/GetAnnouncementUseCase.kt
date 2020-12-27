package com.dms.domain.announcement.usecase

import com.dms.domain.announcement.entity.Announcement
import com.dms.domain.announcement.service.AnnouncementService
import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetAnnouncementUseCase(private val announcementService: AnnouncementService, disposable: CompositeDisposable) : UseCase<String, Result<Announcement>>(disposable) {
    override fun buildUseCase(data: String): Single<Result<Announcement>> =
        announcementService.getAnnouncement(data)
}