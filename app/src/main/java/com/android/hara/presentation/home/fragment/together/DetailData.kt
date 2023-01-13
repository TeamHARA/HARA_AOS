package com.android.hara.presentation.home.fragment.together

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailData(val worryId:Int,val isVoted:Boolean,val isAuthor:Boolean) : Parcelable
