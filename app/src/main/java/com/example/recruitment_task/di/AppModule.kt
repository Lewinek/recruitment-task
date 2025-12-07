package com.example.recruitment_task.di

import androidx.room.Room
import com.example.recruitment_task.data.local.MarketplaceDatabase
import com.example.recruitment_task.data.remote.MartketplaceApi
import com.example.recruitment_task.data.repository.AdRepositoryImpl
import com.example.recruitment_task.domain.repository.AdRepository
import com.example.recruitment_task.domain.usecase.GetAdsUseCase
import com.example.recruitment_task.domain.usecase.GetAllFavoriteAdIdsUseCase
import com.example.recruitment_task.domain.usecase.GetFavouritesAdsUseCase
import com.example.recruitment_task.domain.usecase.ToggleFavoriteAdUseCase
import com.example.recruitment_task.presentation.ads.AdsViewModel
import com.example.recruitment_task.presentation.detail.AdDetailViewModel
import com.example.recruitment_task.presentation.favorites.FavoritesAdsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val ENABLE_LOGGING = true

val appModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = if (ENABLE_LOGGING) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MartketplaceApi::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            MarketplaceDatabase::class.java,
            "marketplace_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<MarketplaceDatabase>().favoriteAdDao() }

    single<AdRepository> {
        AdRepositoryImpl(api = get(), favoriteAdDao = get())
    }

    factory { GetAdsUseCase(repository = get()) }
    factory { GetFavouritesAdsUseCase(repository = get()) }
    factory { ToggleFavoriteAdUseCase(repository = get()) }
    factory { GetAllFavoriteAdIdsUseCase(repository = get()) }

    viewModel {
        AdsViewModel(
            getAdsUseCase = get(),
            toggleFavoriteAdUseCase = get(),
            getAllFavoriteAdIdsUseCase = get()
        )
    }

    viewModel {
        FavoritesAdsViewModel(getFavouritesAdsUseCase = get(), toggleFavoriteAdUseCase = get())
    }

    viewModel {
        AdDetailViewModel(getAllFavoriteAdIdsUseCase = get(), toggleFavoriteAdUseCase = get())
    }
}