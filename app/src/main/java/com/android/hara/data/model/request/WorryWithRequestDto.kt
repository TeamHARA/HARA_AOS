package com.android.hara.data.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorryWithRequestDto(
    @SerialName("categoryId")
    val categoryId: Int, // 1
    @SerialName("content")
    val content: String, // 진로 그거 어떻게 정하는건데..!
    @SerialName("options")
    val options: List<Option>,
    @SerialName("title")
    val title: String // 진로를 결정하고 싶어요 흑흑
) {
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
}