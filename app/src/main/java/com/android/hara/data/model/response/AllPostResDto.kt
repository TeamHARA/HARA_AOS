package com.android.hara.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllPostResDto(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String, // 고민글 조회 성공
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
        val commentCount: Int, // 0
        @SerialName("commentOn")
        val commentOn: Boolean, // false
        @SerialName("content")
        val content: String, // 현재 도서관에서 근로를 하는 중인데, 같이 일하는 사람 중에 눈에 밟히는 사람이 계속 있어 ...너무 내 이상형인데 일단 남자친구가 있는지 없는지 조차도 잘 모르겠는데 ....매일 봐야하는 사이라서 조금 부담스러울 까봐 고민중이야. 먼저 그래도 연락을 해보는게 좋을까 ?
        @SerialName("createdAt")
        val createdAt: String, // 2023.01.07
        @SerialName("finalOptionId")
        val finalOptionId: Int?, // 2
        @SerialName("isAuthor")
        val isAuthor: Boolean, // true
        @SerialName("isVoted")
        val isVoted: Boolean, // true
        @SerialName("option")
        val option: List<Option>,
        @SerialName("title")
        val title: String, // 연락 할까 말까
        @SerialName("worryId")
        val worryId: Int // 1
    ) {
        @Serializable
        data class Option(
            @SerialName("hasImage")
            val hasImage: Boolean, // false
            @SerialName("id")
            val id: Int, // 17
            @SerialName("image")
            val image: String?, // null
            @SerialName("percentage")
            val percentage: Int?, // 33
            @SerialName("title")
            val title: String, // 글1의선택지
            @SerialName("worryWithId")
            val worryWithId: Int // 1
        )
    }
}