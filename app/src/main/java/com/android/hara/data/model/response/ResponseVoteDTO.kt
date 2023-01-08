package com.android.hara.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseVoteDTO(
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean, // true
    @SerialName("message")
    val message: String // 투표 생성 성공
)