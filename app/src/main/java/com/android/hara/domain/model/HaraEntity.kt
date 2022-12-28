package com.android.hara.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HaraEntity (
    @SerialName("HARA")
    val hara: String
)