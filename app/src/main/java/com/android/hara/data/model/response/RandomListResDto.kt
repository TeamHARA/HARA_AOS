package com.android.hara.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomListResDto(
    @SerialName("data")
    val data: List<Data>,
    @SerialName("message")
    val message: String, // 과거고민 목록 조회 실패
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
) {
    @Serializable
    data class Data(
        @SerialName("createdAt")
        val createdAt: String, // 2023.01.06
        @SerialName("id")
        val id: Int, // 1
        @SerialName("title")
        val title: String // 12
    )
}
