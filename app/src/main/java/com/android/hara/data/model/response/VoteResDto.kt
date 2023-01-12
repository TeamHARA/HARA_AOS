package com.android.hara.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoteResDto(
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String, // 투표 생성 성공
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
) {
    @Serializable
    data class Data(
        @SerialName("commentCount")
        val commentCount: Int, // 0
        @SerialName("commentOn")
        val commentOn: Boolean, // false
        @SerialName("content")
        val content: String, // 진로 그거 어떻게 정하는건데..!
        @SerialName("createdAt")
        val createdAt: String, // 2023.01.12
        @SerialName("finalOptionId")
        val finalOptionId: Int?, // null
        @SerialName("isAuthor")
        val isAuthor: Boolean, // false
        @SerialName("isVoted")
        val isVoted: Boolean, // false
        @SerialName("loginUserVoteId")
        val loginUserVoteId: Int, // 0
        @SerialName("option")
        val option: List<Option>,
        @SerialName("title")
        val title: String, // 진로를 결정하고 싶어요 흑흑
        @SerialName("worryId")
        val worryId: Int // 133
    ) {
        @Serializable
        data class Option(
            @SerialName("hasImage")
            val hasImage: Boolean, // false
            @SerialName("id")
            val id: Int, // 199
            @SerialName("image")
            val image: String?,
            @SerialName("percentage")
            val percentage: Int?, // 40
            @SerialName("title")
            val title: String, // 선택지 제목
            @SerialName("worryWithId")
            val worryWithId: Int // 133
        )
    }
}