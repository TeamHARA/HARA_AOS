package com.android.hara.presentation.write.fragment.option.model

import android.net.Uri

data class OptionData(
    val id: Int,
    val title:String,
    val img:Uri,
    val veiwType: Boolean // +버튼 선택지 구분 true면 선택지 아니면 false
)
//TODO img -> uri
