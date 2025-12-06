package com.example.recruitment_task.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.domain.usecase.GetAllFavoriteAdIdsUseCase
import com.example.recruitment_task.domain.usecase.ToggleFavoriteAdUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AdDetailViewModel(
    private val toggleFavoriteAdUseCase: ToggleFavoriteAdUseCase,
    private val getAllFavoriteAdIdsUseCase: GetAllFavoriteAdIdsUseCase
) : ViewModel() {
    val favorites: StateFlow<Set<String>> = getAllFavoriteAdIdsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    fun toggleFavoriteAd(ad: Ad) {
        viewModelScope.launch {
            toggleFavoriteAdUseCase(ad)
        }
    }
}