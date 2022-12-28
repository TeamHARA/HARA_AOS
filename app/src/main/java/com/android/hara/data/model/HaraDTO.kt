package com.android.hara.data.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class HaraDTO(
    @SerialName("HARA")
    val hara: String
)

