package com.android.hara.data.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoteReqDto(
    @SerialName("worryWithId")
    val worryWithId: Int, // 2
    @SerialName("optionId")
    val optionId: Int // 3
)