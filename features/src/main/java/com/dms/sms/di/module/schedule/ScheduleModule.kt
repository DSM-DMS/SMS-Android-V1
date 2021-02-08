package com.dms.sms.di.module.schedule

import com.dms.data.datasource.ScheduleDataSource
import com.dms.data.datasource.ScheduleDataSourceImpl
import com.dms.data.repository.ScheduleRepositoryImpl
import com.dms.domain.schedule.repository.ScheduleRepository
import com.dms.domain.schedule.service.ScheduleService
import com.dms.domain.schedule.service.ScheduleServiceImpl
import com.dms.domain.schedule.usecase.GetScheduleUseCase
import com.dms.sms.di.module.provideScheduleApi
import com.dms.sms.feature.schedule.viewmodel.SchoolScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val scheduleModule = module {
    viewModel { SchoolScheduleViewModel(get()) }

    factory { GetScheduleUseCase(get(), get()) }

    factory<ScheduleService>{ ScheduleServiceImpl(get(), get()) }

    factory<ScheduleRepository>{ ScheduleRepositoryImpl(get()) }

    factory<ScheduleDataSource>{ ScheduleDataSourceImpl(get()) }

    single { provideScheduleApi(get()) }




}