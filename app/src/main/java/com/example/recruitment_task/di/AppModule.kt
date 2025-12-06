package com.example.recruitment_task.di

import com.example.recruitment_task.data.remote.MartketplaceApi
import com.example.recruitment_task.data.repository.AdRepositoryImpl
import com.example.recruitment_task.domain.repository.AdRepository
import com.example.recruitment_task.domain.usecase.GetAdsUseCase
import com.example.recruitment_task.presentation.ads.AdsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MartketplaceApi::class.java) }

    single<AdRepository>{
        AdRepositoryImpl(api = get())
    }

    factory { GetAdsUseCase(repository = get()) }

    viewModel {
        AdsViewModel(getAdsUseCase = get())
    }
}