package com.android.hara.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorryListResDto(
    @SerialName("data")
    val data: List<Data>,
    @SerialName("message")
    val message: String, // 혼자고민 조회 성공
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
) {
    @Serializable
    data class Data(
        @SerialName("categoryId")
        val categoryId: Int, // 1
        @SerialName("title")
        val title: String, // 진로 그거 어떻게 정하는건데..!
        @SerialName("createdAt")
        val createdAt: String, // 2023-01-05T03:47:25.584Z
        @SerialName("finalOption")
        val finalOption: Int?, // 8
        @SerialName("id")
        val id: Int // 24
    )
}
