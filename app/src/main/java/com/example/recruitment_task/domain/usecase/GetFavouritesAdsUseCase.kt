package com.example.recruitment_task.domain.usecase

import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.domain.repository.AdRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesAdsUseCase(private val repository: AdRepository) {
    operator fun invoke(): Flow<List<Ad>> {
        return repository.getFavouritesAds()
    }
}