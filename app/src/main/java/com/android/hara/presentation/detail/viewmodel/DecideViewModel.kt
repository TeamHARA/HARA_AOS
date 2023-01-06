package com.android.hara.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DecideViewModel: ViewModel() {
    private val _enabled = MutableLiveData<Boolean>(false)
    val enabled get() = _enabled
}