package com.example.recruitment_task.domain.usecase

import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.domain.repository.AdRepository
import com.example.recruitment_task.util.ResultWrapper
import kotlinx.coroutines.flow.Flow

class GetAdsUseCase(private val repository: AdRepository) {
    operator fun invoke(): Flow<ResultWrapper<List<Ad>>> {
        return repository.getAds()
    }
}