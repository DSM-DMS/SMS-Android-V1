package com.dms.data.dto.request

import com.dms.domain.outing.request.AccessOutingRequest

data class AccessOutingRequestData(
    val outingUUID: String,
    val action: String
)

fun AccessOutingRequest.toData(): AccessOutingRequestData =
    AccessOutingRequestData(
        outingUUID,
        action
    )