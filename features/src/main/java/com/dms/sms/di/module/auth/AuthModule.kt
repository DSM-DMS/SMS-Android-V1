package com.dms.sms.di.module.auth

import com.dms.data.datasource.AuthDataSource
import com.dms.data.datasource.AuthDataSourceImpl
import com.dms.data.local.auth.LoggedInUserDao
import com.dms.data.local.auth.LoggedInUserDatabase
import com.dms.data.local.sharedpreference.SharedPreferencesStorage
import com.dms.data.remote.AuthApi
import com.dms.data.repository.AuthRepositoryImpl
import com.dms.domain.auth.repository.AuthRepository
import com.dms.domain.auth.service.AuthService
import com.dms.domain.auth.service.AuthServiceImpl
import com.dms.domain.auth.usecase.GetLoginDataUseCase
import com.dms.domain.auth.usecase.LoginDataDeleteUseCase
import com.dms.domain.auth.usecase.SaveLoginDataUseCase
import com.dms.domain.auth.usecase.LoginUseCase
import com.dms.sms.feature.login.AutoLoginViewModel
import com.dms.sms.feature.login.LoginViewModel
import com.dms.sms.feature.login.DeleteLoginDataViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val authModule = module {
    viewModel { LoginViewModel(get(), get()) }

    viewModel { AutoLoginViewModel(get()) }

    viewModel { DeleteLoginDataViewModel(get(),get()) }

    factory { LoginUseCase(get(), get()) }

    factory { SaveLoginDataUseCase(get(), get()) }

    factory { LoginDataDeleteUseCase(get(), get()) }

    factory { GetLoginDataUseCase(get(), get()) }

    factory<AuthService>{ AuthServiceImpl(get(),get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get()) }

    factory<AuthDataSource> { AuthDataSourceImpl(get(),get(), get()) }

    fun provideDao(db : LoggedInUserDatabase) : LoggedInUserDao = db.loggedInUserDao()
    single { LoggedInUserDatabase.getInstance(androidApplication())  }
    single { provideDao(get()) }

    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    single { provideAuthApi(get()) }

    single { SharedPreferencesStorage(androidContext()) }

}