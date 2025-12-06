package com.example.recruitment_task.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdDto(
    @SerialName("actions")
    val actions: ActionsDto?,
    @SerialName("ad-type")
    val adType: String,
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: String,
    @SerialName("image")
    val image: ImageDto?,
    @SerialName("location")
    val location: String?,
    @SerialName("price")
    val price: PriceDto?,
    @SerialName("score")
    val score: String?,
    @SerialName("tracking")
    val tracking: TrackingDto?,
    @SerialName("type")
    val type: String?,
    @SerialName("version")
    val version: String?
)