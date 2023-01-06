package com.android.hara.presentation.write.fragment.how

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HowFragViewModel : ViewModel() {
    private val _answer = MutableLiveData<String>()
    val answer get() = _answer
}
