package com.example.recruitment_task.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.recruitment_task.data.local.entity.FavoriteAdEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAdDao {

    @Query("SELECT * FROM favorites_ads")
    fun getAllFavouritesAds() : Flow<List<FavoriteAdEntity>>
}