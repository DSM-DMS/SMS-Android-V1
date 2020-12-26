package com.dms.sms.di.module.outing

import com.dms.data.datasource.OutingDataSource
import com.dms.data.datasource.OutingDataSourceImpl
import com.dms.data.repository.OutingRepositoryImpl
import com.dms.domain.outing.repository.OutingRepository
import com.dms.domain.outing.service.OutingService
import com.dms.domain.outing.service.OutingServiceImpl
import com.dms.domain.outing.usecase.GetOutingListUseCase
import com.dms.domain.outing.usecase.GetStudentUUIDUseCase
import com.dms.domain.outing.usecase.OutingUseCase
import com.dms.sms.feature.outing.viewmodel.OutingApplyViewModel
import com.dms.sms.feature.outing.viewmodel.OutingHistoryViewModel
import com.dms.sms.feature.outing.viewmodel.OutingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val outingModule: Module = module {
    viewModel { OutingViewModel() }
    viewModel { OutingApplyViewModel(get()) }
    viewModel { OutingHistoryViewModel(get(), get()) }

    factory { OutingUseCase(get(), get()) }
    factory { GetStudentUUIDUseCase(get()) }
    factory { GetOutingListUseCase(get(), get()) }
    factory<OutingService> { OutingServiceImpl(get(), get()) }
    factory<OutingRepository> { OutingRepositoryImpl(get()) }
    factory<OutingDataSource> { OutingDataSourceImpl(get(), get()) }
}