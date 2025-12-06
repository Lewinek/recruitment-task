package com.example.recruitment_task.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdobeDto(
    @SerialName("category")
    val category: String,
    @SerialName("event_name")
    val eventName: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)