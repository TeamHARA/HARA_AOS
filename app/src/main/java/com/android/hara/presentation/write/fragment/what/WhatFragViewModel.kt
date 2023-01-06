package com.android.hara.presentation.write.fragment.what

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WhatFragViewModel : ViewModel() {
    private val _answer = MutableLiveData<String>()

    // 양방향데이터바인딩은 LiveData 사용 불가
    val answer get() = _answer
}
