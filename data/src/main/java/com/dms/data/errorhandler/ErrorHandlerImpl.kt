package com.dms.data.errorhandler

import com.dms.domain.base.Error
import com.dms.domain.errorhandler.ErrorHandler
import retrofit2.HttpException
import java.io.IOException

class ErrorHandlerImpl : ErrorHandler {
    override fun getError(throwable: Throwable): Error {
        return when (throwable) {
            is IOException -> Error.Network
            is HttpException -> {
                when (throwable.code()) {

                    400 -> Error.BadRequest
                    401 -> Error.UnAuthorized
                    403 -> Error.Forbidden
                    404 -> Error.NotFound
                    408 -> Error.Timeout
                    409 -> Error.Conflict
                    423 -> Error.Locked
                    500 -> Error.InternalServer

                    else -> Error.Unknown
                }
            }
            else -> Error.Unknown
        }
    }
}