package com.android.hara.presentation.detail.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DecideData(
    val worryId: Int,
    val worryTitle: String,
    val optionId: List<Int>,
    val optionTitle: List<String>,
    val optionPer: List<Int?>,
    val isAlone: Boolean,
    val includeImg: Boolean
) : Parcelable
