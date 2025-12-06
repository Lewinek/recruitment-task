package com.example.recruitment_task.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdsDto(
    @SerialName("items")
    val items: List<AdDto>?,
    @SerialName("size")
    val size: Int?
)