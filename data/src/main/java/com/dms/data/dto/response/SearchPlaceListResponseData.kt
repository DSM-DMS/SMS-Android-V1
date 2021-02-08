package com.dms.data.dto.response

import com.dms.domain.outing.response.PlaceList
import com.dms.domain.outing.response.SearchPlaceListResponse
import com.google.gson.annotations.SerializedName

data class SearchPlaceListResponseData(
    @SerializedName("item")
    val searchPlace: List<PlaceListData>
)

data class PlaceListData(
    @SerializedName("title")
    val placeTitle: String,
    @SerializedName("link")
    val placeLink: String,
    @SerializedName("category")
    val placeCategory: String,
    @SerializedName("telephone")
    val placeTelephone: String,
    @SerializedName("address")
    val placeAddress: String,
    @SerializedName("roadAddress")
    val placeRoadAddress: String
)

fun SearchPlaceListResponseData.toDomainData(): SearchPlaceListResponse =
    SearchPlaceListResponse(this.searchPlace.map {
        it.toDomainData()
    })

fun PlaceListData.toDomainData(): PlaceList =
    PlaceList(
        this.placeTitle,
        this.placeLink,
        this.placeCategory,
        this.placeTelephone,
        this.placeAddress,
        this.placeRoadAddress
    )