package com.dms.sms.di.base

import com.dms.data.errorhandler.ErrorHandlerImpl
import com.dms.domain.errorhandler.ErrorHandler
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module


val baseModule = module {

    factory { CompositeDisposable() }

    factory<ErrorHandler>{ ErrorHandlerImpl() }

}