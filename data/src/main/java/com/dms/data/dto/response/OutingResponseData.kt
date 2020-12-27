package com.dms.data.dto.response

import com.dms.domain.outing.response.OutingResponse

data class OutingResponseData(
    val outing_uuid: String
)

fun OutingResponseData.toDomainData(): OutingResponse =
    OutingResponse(this.outing_uuid)