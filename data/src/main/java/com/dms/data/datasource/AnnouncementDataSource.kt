package com.dms.data.datasource

import com.dms.data.dto.response.AnnouncementCheckResponseData
import com.dms.data.dto.response.AnnouncementResponseData
import com.dms.data.dto.response.AnnouncementsResponseData
import io.reactivex.Single


interface AnnouncementDataSource {
    fun getAnnouncements(announcementPage : Int) : Single<AnnouncementsResponseData>

    fun getAnnouncement(announcementUUID : String) : Single<AnnouncementResponseData>

    fun searchAnnouncements(searchQuery : String, announcementPage : Int) : Single<AnnouncementsResponseData>

    fun checkAnnouncementUnread(studentUUID : String) : Single<AnnouncementCheckResponseData>
}