package com.example.recruitment_task.domain.repository

import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.util.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AdRepository {
    fun getAds() : Flow<ResultWrapper<List<Ad>>>
    fun getFavouritesAds() : Flow<List<Ad>>
}