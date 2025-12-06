package com.example.recruitment_task.presentation.ads

import com.example.recruitment_task.domain.model.Ad

sealed class AdsUiState {
    object Loading : AdsUiState()
    data class Success(val ads: List<Ad>): AdsUiState()
    data class Error(val message: String): AdsUiState()
    object Empty : AdsUiState()
}