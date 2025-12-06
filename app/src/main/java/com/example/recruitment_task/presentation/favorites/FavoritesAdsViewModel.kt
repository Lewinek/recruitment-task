package com.example.recruitment_task.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.domain.usecase.GetFavouritesAdsUseCase
import com.example.recruitment_task.domain.usecase.ToggleFavoriteAdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesAdsViewModel(
    private val getFavouritesAdsUseCase: GetFavouritesAdsUseCase,
    private val toggleFavoriteAdUseCase: ToggleFavoriteAdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesAdsUIState>(FavoritesAdsUIState.Loading)
    val uiState: StateFlow<FavoritesAdsUIState> = _uiState.asStateFlow()

    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    init {
        loadFavoritesAds()
    }

    private fun loadFavoritesAds() {
        viewModelScope.launch {
            getFavouritesAdsUseCase().collect { favorites ->
                _uiState.value = if (favorites.isEmpty()) {
                    FavoritesAdsUIState.Empty
                } else {
                    FavoritesAdsUIState.Success(favorites)
                }
            }
        }
    }

    fun toggleFavoriteAd(ad: Ad) {
        viewModelScope.launch {
            toggleFavoriteAdUseCase(ad)
        }
    }

}