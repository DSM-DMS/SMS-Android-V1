package com.dms.sms.di.module.announcement

import com.dms.data.datasource.AnnouncementDataSource
import com.dms.data.datasource.AnnouncementDataSourceImpl
import com.dms.data.repository.AnnouncementRepositoryImpl
import com.dms.domain.announcement.repository.AnnouncementRepository
import com.dms.domain.announcement.service.AnnouncementService
import com.dms.domain.announcement.service.AnnouncementServiceImpl
import com.dms.domain.announcement.usecase.CheckAnnouncementUnreadUseCase
import com.dms.domain.announcement.usecase.GetAnnouncementUseCase
import com.dms.domain.announcement.usecase.GetAnnouncementsUseCase
import com.dms.domain.announcement.usecase.SearchAnnouncementsUseCase
import com.dms.sms.feature.announcement.viewmodel.AnnouncementDetailViewModel
import com.dms.sms.di.module.provideAnnouncementApi
import com.dms.sms.feature.announcement.viewmodel.AnnouncementsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val announcementModule: Module = module {

    single { provideAnnouncementApi(get()) }

    single { AnnouncementsViewModel(get(), get(),get(),get()) }

    single { AnnouncementDetailViewModel(get(),get()) }

    factory { CheckAnnouncementUnreadUseCase(get(), get()) }

    factory { GetAnnouncementsUseCase(get(), get()) }

    factory { GetAnnouncementUseCase(get(), get()) }

    factory { SearchAnnouncementsUseCase(get(), get()) }

    factory<AnnouncementService>{ AnnouncementServiceImpl(get(),get()) }

    factory<AnnouncementRepository> { AnnouncementRepositoryImpl(get()) }

    factory<AnnouncementDataSource> { AnnouncementDataSourceImpl(get()) }

}