package com.android.hara.presentation.write.fragment.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContentFragViewModel : ViewModel() {
    private val _answer = MutableLiveData<String>()
    val answer get() = _answer
}
