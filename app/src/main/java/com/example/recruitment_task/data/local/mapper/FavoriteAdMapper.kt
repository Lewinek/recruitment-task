package com.example.recruitment_task.data.local.mapper

import com.example.recruitment_task.data.local.entity.FavoriteAdEntity
import com.example.recruitment_task.domain.model.Ad

fun FavoriteAdEntity.toDomain(): Ad {
    return Ad(
        id = id,
        title = title,
        price = price,
        location = location,
        image = image
    )
}