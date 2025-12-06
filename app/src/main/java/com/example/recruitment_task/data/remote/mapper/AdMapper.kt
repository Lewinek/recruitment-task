package com.example.recruitment_task.data.remote.mapper

import com.example.recruitment_task.data.remote.dto.AdDto
import com.example.recruitment_task.domain.model.Ad


fun AdDto.toDomain(): Ad {
    return Ad(
        id = id,
        title = description ?: "",
        image = image?.url ?: "",
        price = price?.value ?: "",
        location = location ?: ""
    )
}