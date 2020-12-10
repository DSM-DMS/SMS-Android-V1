package com.dms.domain.errorhandler

import com.dms.domain.base.Error

interface ErrorHandler {
    fun getError(throwable: Throwable) : Error
}