package com.dms.sms.feature.outing.model

import com.dms.domain.outing.request.AccessOutingRequest

data class AccessOutingModel(
    val outingUUID: String,
    val action: String
)

fun AccessOutingModel.toDomain(): AccessOutingRequest =
    AccessOutingRequest(
        outingUUID,
        action
    )