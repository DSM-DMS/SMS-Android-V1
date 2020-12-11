package com.dms.sms.di.module.account

import com.dms.data.datasource.AccountDataSource
import com.dms.data.datasource.AccountDataSourceImpl
import com.dms.data.repository.AccountRepositoryImpl
import com.dms.domain.account.repository.AccountRepository
import com.dms.domain.account.service.AccountService
import com.dms.domain.account.service.AccountServiceImpl
import com.dms.domain.account.usecase.GetStudentUseCase
import org.koin.dsl.module


val accountModule = module {

    factory { GetStudentUseCase(get(), get()) }

    factory<AccountService>{ AccountServiceImpl(get(),get()) }

    factory<AccountRepository> { AccountRepositoryImpl(get()) }

    factory<AccountDataSource> { AccountDataSourceImpl(get()) }

}