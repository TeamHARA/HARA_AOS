package com.android.hara.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class DecideAloneReqDto(
    val worryAloneId: Int,
    val chosenOptionId: Int
)
