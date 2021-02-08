package com.dms.domain.outing.request

data class AccessOutingRequest(
    val outingUUID: String,
    val action: String
)