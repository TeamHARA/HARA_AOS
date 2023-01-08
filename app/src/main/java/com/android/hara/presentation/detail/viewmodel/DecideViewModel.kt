package com.android.hara.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DecideViewModel @Inject constructor(): ViewModel() {
    private val _enabled = MutableLiveData<Boolean>(false)
    val enabled get() = _enabled
}