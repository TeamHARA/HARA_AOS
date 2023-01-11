package com.android.hara.presentation.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hara.presentation.write.fragment.proscons.model.PronsData

class WriteViewModel : ViewModel() {
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress


    var title = "" // 제목 1번
    var content = "" // 내용 1번
    private val _titleList = mutableListOf<String>("", "", "", "") // 선택지 2번
    val titleList get() = _titleList
    private val _pronsList = mutableListOf<PronsData>() // 장단점 리스트 3번
    val pronsList get() = _pronsList
    var categoty = -1 // 카테고리 4번

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
