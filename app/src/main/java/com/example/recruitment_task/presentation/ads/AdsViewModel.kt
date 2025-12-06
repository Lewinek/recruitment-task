package com.example.recruitment_task.presentation.ads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruitment_task.domain.usecase.GetAdsUseCase
import com.example.recruitment_task.util.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AdsViewModel(private val getAdsUseCase: GetAdsUseCase): ViewModel() {

    private val _uiState = MutableStateFlow<AdsUiState>(AdsUiState.Loading)
    val uiState: StateFlow<AdsUiState> = _uiState.asStateFlow()

    init {
        loadAds()
    }

    fun loadAds(){
        viewModelScope.launch {
            getAdsUseCase().collect { result->
                _uiState.value = when (result){
                    is ResultWrapper.Loading -> AdsUiState.Loading
                    is ResultWrapper.Success -> {
                        if(result.data.isEmpty()){
                            AdsUiState.Empty
                        } else {
                            AdsUiState.Success(result.data)
                        }
                    }
                    is ResultWrapper.Error -> AdsUiState.Error(result.message)
                }
            }
        }
    }
}