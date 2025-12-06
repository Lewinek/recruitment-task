package com.example.recruitment_task.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    @SerialName("height")
    val height: Int?,
    @SerialName("scalable")
    val scalable: Boolean?,
    @SerialName("type")
    val type: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("width")
    val width: Int?
)