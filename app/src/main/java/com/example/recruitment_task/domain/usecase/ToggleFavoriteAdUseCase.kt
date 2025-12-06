package com.example.recruitment_task.domain.usecase

import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.domain.repository.AdRepository


class ToggleFavoriteAdUseCase(private val repository: AdRepository) {
    suspend operator fun invoke(ad: Ad) {
        return repository.toggleFavoriteAd(ad)
    }
}