package com.dms.data.remote

import com.dms.data.dto.response.AnnouncementResponseData
import com.dms.data.dto.response.AnnouncementsResponseData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnnouncementApi {

    @GET("v1/announcements/types/{type}")
    fun getAnnouncements(@Path("type") type : String, @Query("start") start : Int,@Query("count") count : Int) : Single<AnnouncementsResponseData>

    @GET("v1/announcements/uuid/{announcement_uuid}")
    fun getAnnouncement(@Path("announcement_uuid") announcementUUID : String) : Single<AnnouncementResponseData>

    @GET("v1/announcements/types/school/query/{search_query}")
    fun searchAnnouncement(@Path("search_query") searchQuery : String, @Query("start") start : Int,@Query("count") count : Int) : Single<AnnouncementsResponseData>
}