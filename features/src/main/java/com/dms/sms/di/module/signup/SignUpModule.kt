package com.dms.sms.di.module.signup

import com.dms.data.datasource.SignUpDataSource
import com.dms.data.datasource.SignUpDataSourceImpl
import com.dms.data.repository.SignUpRepositoryImpl
import com.dms.domain.signup.repository.SignUpRepository
import com.dms.domain.signup.service.SignUpService
import com.dms.domain.signup.service.SignUpServiceImpl
import com.dms.domain.signup.usecase.GetNoAccountStudentInfoUseCase
import com.dms.domain.signup.usecase.SignUpUseCase
import com.dms.sms.di.module.provideSignUpApi
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import com.dms.sms.feature.signup.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val signUpModule = module {
    single { SignUpViewModel(get(),get(),get(),get(),get()) }

    factory { SignUpUseCase(get(), get()) }
    factory { GetNoAccountStudentInfoUseCase(get(), get()) }


    factory<SignUpService>{ SignUpServiceImpl(get(),get()) }

    factory<SignUpRepository> { SignUpRepositoryImpl(get()) }

    factory<SignUpDataSource> { SignUpDataSourceImpl(get()) }

    single { provideSignUpApi(get()) }

}