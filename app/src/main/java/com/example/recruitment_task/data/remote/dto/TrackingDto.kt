package com.example.recruitment_task.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrackingDto(
    @SerialName("adobe")
    val adobe: AdobeDto?,
    @SerialName("ec")
    val ec: EcDto?
)