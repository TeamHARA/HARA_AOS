package com.android.hara.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class DecideWithReqDto(
    val worryWithId: Int,
    val chosenOptionId: Int
)
