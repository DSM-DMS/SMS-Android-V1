package com.dms.domain.outing.response

data class SearchPlaceListResponse(
    val searchPlace: List<PlaceList>
)

data class PlaceList(
    val placeTitle: String,
    val placeLink: String,
    val placeCategory: String,
    val placeTelephone: String,
    val placeAddress: String,
    val placeRoadAddress: String
)