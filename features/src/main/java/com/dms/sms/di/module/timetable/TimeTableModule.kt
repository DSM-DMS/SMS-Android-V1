package com.dms.sms.di.module.timetable

import com.dms.data.datasource.TimeTableDataSource
import com.dms.data.datasource.TimeTableDataSourceImpl
import com.dms.data.repository.TimeTableRepositoryImpl
import com.dms.domain.timetable.repository.TimeTableRepository
import com.dms.domain.timetable.service.TimeTableService
import com.dms.domain.timetable.service.TimeTableServiceImpl
import com.dms.domain.timetable.usecase.TimeTableUseCase
import com.dms.sms.di.module.provideTimeTableApi
import com.dms.sms.feature.timetable.viewmodel.TimeTableViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val timeTableModule: Module = module {
    viewModel { TimeTableViewModel(get()) }

    factory { TimeTableUseCase(get(),get()) }
    factory<TimeTableService> { TimeTableServiceImpl(get(), get()) }
    factory<TimeTableRepository> { TimeTableRepositoryImpl(get()) }
    factory<TimeTableDataSource> { TimeTableDataSourceImpl(get()) }

    single { provideTimeTableApi(get()) }
}