package com.example.recruitment_task.data.local.dao

import androidx.room.Dao
import com.example.recruitment_task.data.local.entity.FavoriteAdEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAdDao {

    fun getAllFavouritesAds() : Flow<List<FavoriteAdEntity>>
}