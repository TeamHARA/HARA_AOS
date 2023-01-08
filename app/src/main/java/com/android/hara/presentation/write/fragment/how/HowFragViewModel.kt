package com.android.hara.presentation.write.fragment.how

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HowFragViewModel : ViewModel() {
    private val _answer = MutableLiveData<String>()
    val answer get() = _answer

    private val _isAloneSelected = MutableLiveData<Boolean>()
    val isAloneSelected get() = _isAloneSelected

    private val _isWithSelected = MutableLiveData<Boolean>()
    val isWithSelected get() = _isWithSelected

    private val _enabled = MutableLiveData<Boolean>()
    val enabled get() = _enabled

    init {
        _isAloneSelected.value = false
        isAloneSelected.value = _isAloneSelected.value
        _isWithSelected.value = false
        isWithSelected.value = _isWithSelected.value
        _enabled.value = false
        enabled.value = _enabled.value
    }
}
