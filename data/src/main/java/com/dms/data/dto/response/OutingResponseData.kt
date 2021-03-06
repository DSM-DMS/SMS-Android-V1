package com.dms.data.dto.response

import com.dms.domain.outing.response.OutingResponse

data class OutingResponseData(
    val outing_uuid: String,
    val code: Int
)

fun OutingResponseData.toDomainData(): OutingResponse =
    OutingResponse(
        this.outing_uuid,
        this.code
    )