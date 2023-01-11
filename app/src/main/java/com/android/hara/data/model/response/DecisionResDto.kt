package com.android.hara.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DecisionResDto(
    @SerialName("message")
    val message: String, // 나의고민 최종결정 성공
    @SerialName("status")
    val status: Int, // 200
    @SerialName("success")
    val success: Boolean // true
)
