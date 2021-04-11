package com.dms.data.datasource

import com.dms.data.dto.response.AnnouncementCheckResponseData
import com.dms.data.dto.response.AnnouncementResponseData
import com.dms.data.dto.response.AnnouncementsResponseData
import com.dms.data.remote.AnnouncementApi
import io.reactivex.Single

class AnnouncementDataSourceImpl(private val announcementApi: AnnouncementApi) : AnnouncementDataSource {
    override fun getAnnouncements(announcementPage : Int): Single<AnnouncementsResponseData> =
        announcementApi.getAnnouncements("school",announcementPage,8)

    override fun getAnnouncement(announcementUUID: String): Single<AnnouncementResponseData> =
        announcementApi.getAnnouncement(announcementUUID)

    override fun searchAnnouncements(searchQuery: String, announcementPage : Int): Single<AnnouncementsResponseData> =
        announcementApi.searchAnnouncement(searchQuery, announcementPage, 8 )

    override fun checkAnnouncementUnread(studentUUID: String): Single<AnnouncementCheckResponseData> =
        announcementApi.checkAnnouncementsUnread(studentUUID)
}