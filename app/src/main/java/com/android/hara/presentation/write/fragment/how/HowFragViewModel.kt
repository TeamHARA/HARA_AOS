package com.android.hara.presentation.write.fragment.how

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HowFragViewModel @Inject constructor(): ViewModel() {
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
