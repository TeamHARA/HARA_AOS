package com.android.hara.presentation.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hara.presentation.write.fragment.option.model.OptionData

class WriteViewModel : ViewModel() {
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress

    private val _title = ""
    val title get() = _title
    private val _content = ""
    val content get() = _content
    private val _titleList = mutableListOf<String?>()
    val titleList get() = _titleList


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
