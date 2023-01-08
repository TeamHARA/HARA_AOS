package com.android.hara.presentation.write.fragment.option

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class OptionFragViewModel : ViewModel() {
    private val _optionNum = MutableLiveData<Int>()
    val optionNum get() = _optionNum

    private val _enabled = MutableLiveData<Boolean>()
    val enabled get() = _enabled

    private val _string1 = MutableLiveData<String>()
    val string1 get() = _string1
    private val _string2 = MutableLiveData<String>()
    val string2 get() = _string2
    private val _string3 = MutableLiveData<String>()
    val string3 get() = _string3
    private val _string4 = MutableLiveData<String>()
    val string4 get() = _string4

    init {
        _optionNum.value = 2
        optionNum.value = _optionNum.value
        _enabled.value = false
        enabled.value = _enabled.value
    }

    fun addOption() {
        _optionNum.value = _optionNum.value!! + 1
        optionNum.value = _optionNum.value
    }

    fun removeOption() {
        _optionNum.value = _optionNum.value!! - 1
        optionNum.value = _optionNum.value
        setText(_optionNum)
    }

    fun setText(num: MutableLiveData<Int>) {

    }

    fun setEnable() {
        Timber.e("SET ENABLELE")
        _enabled.value = when (optionNum.value) {
            2 -> !_string1.value.isNullOrEmpty() && !_string2.value.isNullOrEmpty()
            3 -> !_string1.value.isNullOrEmpty() && !_string2.value.isNullOrEmpty() && !_string3.value.isNullOrEmpty()
            4 -> !_string1.value.isNullOrEmpty() && !_string2.value.isNullOrEmpty() && !_string3.value.isNullOrEmpty() && !_string4.value.isNullOrEmpty()
            else -> {
                return
            }
        }
        enabled.value = _enabled.value
    }
}
