package com.example.recruitment_task.data.local.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EcDto(
    @SerialName("click")
    val click: List<String>,
    @SerialName("inScreen")
    val inScreen: List<String>
)