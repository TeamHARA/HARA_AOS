package com.android.hara.presentation.home.fragment.together

import android.os.Parcelable
import com.android.hara.data.model.response.AllPostResDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailData(
    val worryId: Int,
    val isVoted: Boolean,
    val isAuthor: Boolean
) : Parcelable

// val opt: List<String?>
// [옵션1] 장점 opt[0], 단점 opt[1]
// [옵션2] 장점 opt[2], 단점 opt[3]
