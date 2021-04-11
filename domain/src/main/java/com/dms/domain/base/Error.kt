package com.dms.domain.base

sealed class Error {
    object Network : Error()

    object BadRequest : Error()

    object UnAuthorized : Error()

    object Forbidden : Error()

    object NotFound : Error()

    object Timeout : Error()

    object Conflict : Error()

    object InternalServer : Error()

    object Unknown : Error()

    object Locked : Error()
}