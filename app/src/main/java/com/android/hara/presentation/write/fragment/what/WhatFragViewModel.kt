package com.android.hara.presentation.write.fragment.what

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WhatFragViewModel : ViewModel() {
    private val _title = MutableLiveData<String>()
    val title get() = _title

    private val _content = MutableLiveData<String>()
    val content get() = _content

    private val _enabled = MutableLiveData<Boolean>()
    val enabled get() = _enabled

    fun setNextBtn() {
        _enabled.value = !_title.value.isNullOrEmpty() && !_content.value.isNullOrEmpty()
    }
}
