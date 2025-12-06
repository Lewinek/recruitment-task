package com.example.recruitment_task.presentation.favorites

import com.example.recruitment_task.domain.model.Ad

sealed class FavoritesAdsUIState {
    object Loading : FavoritesAdsUIState()
    data class Success(val favorites: List<Ad>): FavoritesAdsUIState()
    object Empty : FavoritesAdsUIState()
}
