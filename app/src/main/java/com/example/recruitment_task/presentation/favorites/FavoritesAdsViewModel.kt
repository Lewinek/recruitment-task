package com.example.recruitment_task.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruitment_task.domain.usecase.GetFavouritesAdsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesAdsViewModel(private val getFavouritesAdsUseCase: GetFavouritesAdsUseCase): ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesAdsUIState>(FavoritesAdsUIState.Loading)
    val uiState: StateFlow<FavoritesAdsUIState> = _uiState.asStateFlow()

    init {
        loadFavoritesAds()
    }

    private fun loadFavoritesAds(){
        viewModelScope.launch {
            getFavouritesAdsUseCase().collect { favorites ->
                _uiState.value = if (favorites.isEmpty()){
                    FavoritesAdsUIState.Empty
                } else {
                    FavoritesAdsUIState.Success(favorites)
                }
            }
        }
    }
}