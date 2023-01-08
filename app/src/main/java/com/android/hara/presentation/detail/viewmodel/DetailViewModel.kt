package com.android.hara.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel: ViewModel() {
    // selected number: 현재 선택된 옵션#를 저장, 아무 것도 안 선택돼있으면 0이 저장됨
    private val _sNum = MutableLiveData<Int>(0)
    val sNum get() = _sNum
}