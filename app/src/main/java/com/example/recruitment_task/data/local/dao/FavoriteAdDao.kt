package com.example.recruitment_task.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recruitment_task.data.local.entity.FavoriteAdEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAdDao {

    @Query("SELECT * FROM favorites_ads")
    fun getAllFavouritesAds() : Flow<List<FavoriteAdEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites_ads WHERE id = :adId)")
    suspend fun isFavoriteAd(adId: String) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteAd(ad: FavoriteAdEntity)

    @Query("DELETE FROM favorites_ads WHERE id= :adId")
    suspend fun deleteFavoriteAd(adId: String)

    @Query("SELECT id FROM favorites_ads")
    fun getAllFavoriteAdIds() : Flow<List<String>>
}