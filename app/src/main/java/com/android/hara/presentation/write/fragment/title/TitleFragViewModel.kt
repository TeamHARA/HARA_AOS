package com.android.hara.presentation.write.fragment.title

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TitleFragViewModel : ViewModel() {
    private val _answer = MutableLiveData<String>()

    // 양방향데이터바인딩은 LiveData 사용 불가
    val answer get() = _answer
}
