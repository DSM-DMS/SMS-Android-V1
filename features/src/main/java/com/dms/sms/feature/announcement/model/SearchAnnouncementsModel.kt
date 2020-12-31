package com.dms.sms.feature.announcement.model

import com.dms.domain.announcement.request.SearchAnnouncementsRequest

data class SearchAnnouncementsModel(val searchQuery : String ,val announcementsPage : Int)


fun SearchAnnouncementsModel.toDomainData() : SearchAnnouncementsRequest =
    SearchAnnouncementsRequest(this.searchQuery, this.announcementsPage)
