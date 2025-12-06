package com.example.recruitment_task.data.local.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActionsDto(
    @SerialName("block")
    val block: List<String>
)