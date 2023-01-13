package com.android.hara.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailAloneResDto(
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String, // 혼자고민 상세조회 성공
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
) {
    @Serializable
    data class Data(
        @SerialName("category")
        val category: String, // 고민내용
        @SerialName("createdAt")
        val createdAt: String, // 2023.01.06
        @SerialName("finalOption")
        val finalOption: Int?,// 86
        @SerialName("options")
        val options: List<Option>,
        @SerialName("worryContent")
        val worryContent: String, // 고민내용
        @SerialName("worryTitle")
        val worryTitle: String // 고민제목
    ) {
        @Serializable
        data class Option(
            @SerialName("advantage")
            val advantage: String? = "", // 장점
            @SerialName("disadvantage")
            val disadvantage: String? = "", // 단점
            @SerialName("hasImage")
            val hasImage: Boolean, // false
            @SerialName("id")
            val id: Int, // 83
            @SerialName("image")
            val image: String?, // image
            @SerialName("title")
            val title: String, // 옵션1
            @SerialName("worryAloneId")
            val worryAloneId: Int // 38
        )
    }
}