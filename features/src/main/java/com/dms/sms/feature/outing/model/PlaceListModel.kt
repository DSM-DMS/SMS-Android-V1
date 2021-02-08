package com.dms.sms.feature.outing.model

import com.dms.domain.outing.response.PlaceList

data class PlaceListModel(
    val placeTitle: String,
    val placeLink: String,
    val placeCategory: String,
    val placeTelephone: String,
    val placeAddress: String,
    val placeRoadAddress: String
)

fun PlaceList.toModel(): PlaceListModel =
    PlaceListModel(
        this.placeTitle,
        this.placeLink,
        this.placeCategory,
        this.placeTelephone,
        this.placeAddress,
        this.placeRoadAddress
    )