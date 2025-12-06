package com.example.recruitment_task.domain.usecase

import com.example.recruitment_task.domain.repository.AdRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteAdIdsUseCase(private val repository: AdRepository) {
     operator fun invoke(): Flow<Set<String>> {
        return repository.getAllFavoriteAdIds()
    }
}