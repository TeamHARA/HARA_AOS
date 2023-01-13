package com.android.hara.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoteResDto(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String, // 투표 생성 성공
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
) {
    @Serializable
    data class Data(
        @SerialName("category")
        val category: String, // 패션/뷰티
        @SerialName("commentCount")
        val commentCount: Int, // 0
        @SerialName("commentOn")
        val commentOn: Boolean, // true
        @SerialName("content")
        val content: String, // 이번에 팀원들한테 단체 후드집업을 선물해주려고 하는데요!이런 디자인 어떤지 궁금해요 ㅎㅎ 팀원들한텐 서프라이즈로 줄 거라 투표 받을 순 없어서 ㅎㅎ해라이너분들이 제 선택에 도움을 주세요!
        @SerialName("createdAt")
        val createdAt: String, // 2023.01.09
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
        val title: String, // 후드집업 어떤 디자인으로 만들까?
        @SerialName("worryId")
        val worryId: Int // 9
    ) {
        @Serializable
        data class Option(
            @SerialName("advantage")
            val advantage: String?, // null
            @SerialName("disadvantage")
            val disadvantage: String?, // null
            @SerialName("hasImage")
            val hasImage: Boolean, // false
            @SerialName("id")
            val id: Int, // 472
            @SerialName("image")
            val image: String?, // null
            @SerialName("percentage")
            val percentage: Int, // 0
            @SerialName("title")
            val title: String, // 1
            @SerialName("worryWithId")
            val worryWithId: Int // 9
        )
    }
}