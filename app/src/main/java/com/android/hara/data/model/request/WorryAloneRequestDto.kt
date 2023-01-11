package com.android.hara.data.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorryAloneRequestDto(
    @SerialName("categoryId")
    val categoryId: Int, // 1
    @SerialName("content")
    val content: String, // 진로 그거 어떻게 정하는건데..!
    @SerialName("options")
    val options: List<Option>,
    @SerialName("title")
    val title: String // 진로를 결정하고 싶어요 흑흑
)