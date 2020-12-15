package com.dms.data.dto.response

import com.dms.domain.outing.dto.OutingResponseData

data class OutingResponse(
    val outing_uuid: String
)

fun OutingResponse.toDomainData(): OutingResponseData =
    OutingResponseData(this.outing_uuid)