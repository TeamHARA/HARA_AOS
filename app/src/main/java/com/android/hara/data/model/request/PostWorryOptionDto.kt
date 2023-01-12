package com.android.hara.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Option(
    @SerialName("advantage")
    val advantage: String, // 장점의 이유
    @SerialName("disadvantage")
    val disadvantage: String, // 단점의 이유
    @SerialName("hasImage")
    val hasImage: Boolean, // false
    @SerialName("image")
    val image: String,
    @SerialName("title")
    val title: String // 선택지 제목
)
