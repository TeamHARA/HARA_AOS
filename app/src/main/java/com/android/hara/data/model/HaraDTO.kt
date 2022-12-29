package com.android.hara.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HaraDTO(
    @SerialName("HARA")
    val hara: String
)

