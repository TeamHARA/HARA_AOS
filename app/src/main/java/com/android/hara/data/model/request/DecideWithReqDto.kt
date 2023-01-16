package com.android.hara.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DecideWithReqDto(
    @SerialName("worryWithId")
    val worryWithId: Int,
    @SerialName("chosenOptionId")
    val chosenOptionId: Int
)
