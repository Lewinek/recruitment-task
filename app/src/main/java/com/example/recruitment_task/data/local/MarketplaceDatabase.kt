package com.example.recruitment_task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recruitment_task.data.local.dao.FavoriteAdDao
import com.example.recruitment_task.data.local.entity.FavoriteAdEntity

@Database(
    entities = [FavoriteAdEntity::class],
    version = 1,
    exportSchema = false
)

abstract class MarketplaceDatabase : RoomDatabase() {
    abstract fun favoriteAdDao(): FavoriteAdDao
}