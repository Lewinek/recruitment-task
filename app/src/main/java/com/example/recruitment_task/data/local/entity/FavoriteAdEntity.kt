package com.example.recruitment_task.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorites_ads")
data class FavoriteAdEntity(
    @PrimaryKey val id: String,
    val title: String,
    val price: String?,
    val location: String,
    val image: String,
)
