package com.dms.sms.di.module.auth

import com.dms.data.datasource.AuthDataSource
import com.dms.data.datasource.AuthDataSourceImpl
import com.dms.data.repository.AuthRepositoryImpl
import com.dms.domain.auth.repository.AuthRepository
import com.dms.domain.auth.service.AuthService
import com.dms.domain.auth.service.AuthServiceImpl
import com.dms.domain.auth.usecase.LoginUseCase
import com.dms.sms.feature.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { LoginViewModel(get()) }

    factory { LoginUseCase(get(), get()) }

    factory<AuthService>{ AuthServiceImpl(get(),get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get()) }

    factory<AuthDataSource> { AuthDataSourceImpl(get()) }

}