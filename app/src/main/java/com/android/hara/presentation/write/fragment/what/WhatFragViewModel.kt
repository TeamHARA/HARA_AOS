package com.android.hara.presentation.write.fragment.what

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WhatFragViewModel @Inject constructor(): ViewModel() {
    private val _title = MutableLiveData<String>()
    val title get() = _title

    private val _content = MutableLiveData<String>()
    val content get() = _content

    private val _enabled = MutableLiveData<Boolean>()
    val enabled get() = _enabled

    fun setEnabled() {
        _enabled.value = !_title.value.isNullOrEmpty() && !_content.value.isNullOrEmpty()
    }
}
