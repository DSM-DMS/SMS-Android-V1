package com.dms.sms.di.module.mypage

import com.dms.data.datasource.MyPageDataSource
import com.dms.data.datasource.MyPageDataSourceImpl
import com.dms.data.remote.AccountApi
import com.dms.data.remote.MyPageApi
import com.dms.data.repository.MyPageRepositoryImpl
import com.dms.domain.mypage.repository.MyPageRepository
import com.dms.domain.mypage.service.MyPageService
import com.dms.domain.mypage.service.MyPageServiceImpl
import com.dms.domain.mypage.usecase.GetStudentUUIDUseCase
import com.dms.domain.mypage.usecase.GetUserProfileUseCase
import com.dms.sms.feature.mypage.viewmodel.MyPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val myPageModule: Module = module {

    fun provideMyPageApi(retrofit: Retrofit): MyPageApi =
        retrofit.create(MyPageApi::class.java)

    viewModel { MyPageViewModel(get(), get()) }

    factory { GetStudentUUIDUseCase(get(), get()) }
    factory { GetUserProfileUseCase(get(), get()) }
    factory<MyPageService> { MyPageServiceImpl(get(), get()) }
    factory<MyPageRepository> { MyPageRepositoryImpl(get()) }
    factory<MyPageDataSource> { MyPageDataSourceImpl(get(), get()) }

    single { provideMyPageApi(get()) }
}