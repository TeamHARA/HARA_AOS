package com.android.hara.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailWithResDto(
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String, // 함께고민 상세조회 성공
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
) {
    @Serializable
    data class Data(
        @SerialName("category")
        val category: String, // 일상
        @SerialName("commentCount")
        val commentCount: Int, // 2
        @SerialName("comments")
        val comments: List<Comment>?,
        @SerialName("createdAt")
        val createdAt: String, // 2023.01.07
        @SerialName("finalOption")
        val finalOption: Int?, // 2
        @SerialName("isAuthor")
        val isAuthor: Boolean, // false
        @SerialName("options")
        val options: List<Option>,
        @SerialName("worryContent")
        val worryContent: String, // userId = 3
        @SerialName("worryTitle")
        val worryTitle: String // userId = 3
    ) {
        @Serializable
        data class Comment(
            @SerialName("content")
            val content: String, // 댓글2
            @SerialName("createdAt")
            val createdAt: String, // 2023.01.09
            @SerialName("userImage")
            val userImage: String, // 탈퇴했거나 가입하지 않은 유저입니다.
            @SerialName("userNickName")
            val userNickName: String, // 댓글2
        )

        @Serializable
        data class Option(
            @SerialName("advantage")
            val advantage: String? = "",
            @SerialName("disadvantage")
            val disadvantage: String? = "", // 단점의 이유
            @SerialName("hasImage")
            val hasImage: Boolean, // false
            @SerialName("id")
            val id: Int, // 3
            @SerialName("image")
            val image: String?, // null
            @SerialName("percentage")
            val percentage: Int?, // 50
            @SerialName("title")
            val title: String, // 글 2의 선택지
            @SerialName("worryWithId")
            val worryWithId: Int // 2
        )
    }
}