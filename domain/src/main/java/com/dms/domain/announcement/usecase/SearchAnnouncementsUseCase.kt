package com.dms.domain.announcement.usecase

import com.dms.domain.announcement.entity.Announcements
import com.dms.domain.announcement.request.SearchAnnouncementsRequest
import com.dms.domain.announcement.service.AnnouncementService
import com.dms.domain.base.Result
import com.dms.domain.base.UseCase
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class SearchAnnouncementsUseCase (private val announcementService: AnnouncementService, disposable: CompositeDisposable) : UseCase<SearchAnnouncementsRequest, Result<Announcements>>(disposable) {
    override fun buildUseCase(data: SearchAnnouncementsRequest): Single<Result<Announcements>> =
        announcementService.searchAnnouncements(data.searchQuery, data.announcementsPage)
}