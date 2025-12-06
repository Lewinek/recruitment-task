package com.example.recruitment_task.domain.usecase

import com.example.recruitment_task.domain.repository.AdRepository


class IsFavoriteAdUseCase(private val repository: AdRepository) {
    suspend operator fun invoke(adId: String): Boolean {
        return repository.isFavoriteAd(adId)
    }
}