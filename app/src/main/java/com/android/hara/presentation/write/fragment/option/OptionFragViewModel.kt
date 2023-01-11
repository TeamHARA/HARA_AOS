package com.android.hara.presentation.write.fragment.option

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class OptionFragViewModel : ViewModel() {
    private val _localTitleList = mutableListOf<String>()

    fun setLocalTitle(position : Int, text:String){
        _localTitleList[position] = text
    }
}
