package com.android.hara.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DecideAloneReqDto(
    @SerialName("worryAloneId")
    val worryAloneId: Int,
    @SerialName("chosenOptionId")
    val chosenOptionId: Int
)
