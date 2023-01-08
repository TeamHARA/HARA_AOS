package com.android.hara.presentation.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteViewModel : ViewModel() {
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress

    init {
        _progress.value = 1
    }

    fun addProgress() {
        // viewModel은 (=) 사용해서 변경해야 함!!!!! ( _progress.value?.plus(-1) 에러 )
        _progress.value = _progress.value!! + 1
    }

    fun subProgress() {
        _progress.value = _progress.value!! - 1
    }
}
