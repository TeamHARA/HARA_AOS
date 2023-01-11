package com.android.hara.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnesecResDto(
    @SerialName("data")
    val data: Data,
    @SerialName("message")
    val message: String, // 랜덤 답변 조회 성공
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
) {
    @Serializable
    data class Data(
        @SerialName("content")
        val content: String, // 야식 먹을까?
        @SerialName("id")
        val id: Int // 1
    )
}
