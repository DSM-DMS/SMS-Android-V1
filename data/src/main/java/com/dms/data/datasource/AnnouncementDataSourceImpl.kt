package com.dms.data.datasource

import com.dms.data.dto.response.AnnouncementResponseData
import com.dms.data.dto.response.AnnouncementsResponseData
import com.dms.data.remote.AnnouncementApi
import io.reactivex.Single

class AnnouncementDataSourceImpl(private val announcementApi: AnnouncementApi) : AnnouncementDataSource {
    override fun getAnnouncements(announcementPage : Int): Single<AnnouncementsResponseData> =
        announcementApi.getAnnouncements("school",announcementPage,8)

    override fun getAnnouncement(announcementUUID: String): Single<AnnouncementResponseData> =
        announcementApi.getAnnouncement(announcementUUID)

}