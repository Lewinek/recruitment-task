package com.example.recruitment_task.data.repository

import com.example.recruitment_task.data.local.dao.FavoriteAdDao
import com.example.recruitment_task.data.local.mapper.toDomain
import com.example.recruitment_task.data.remote.MartketplaceApi
import com.example.recruitment_task.data.remote.mapper.toDomain
import com.example.recruitment_task.domain.model.Ad
import com.example.recruitment_task.domain.repository.AdRepository
import com.example.recruitment_task.util.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AdRepositoryImpl(
   private val api: MartketplaceApi,
    private val favoriteAdDao: FavoriteAdDao
) : AdRepository {
    override fun getAds(): Flow<ResultWrapper<List<Ad>>> = flow {
        emit(ResultWrapper.Loading)
        try {
            val response = api.getAds()
            val ads = response.items.map {it.toDomain()}
            emit(ResultWrapper.Success(ads))
        } catch (e: Exception){
            emit(ResultWrapper.Error("Failed to load ads: ${e.message}", e))
        }
    }

    override fun getFavouritesAds(): Flow<List<Ad>> {
       return favoriteAdDao.getAllFavouritesAds().map { entities -> entities.map { it.toDomain() } }
    }
}