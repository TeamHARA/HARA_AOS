package com.android.hara.presentation.write.fragment.option

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OptionFragViewModel @Inject constructor(): ViewModel() {
    private val _localTitleList = mutableListOf<String>()

    fun setLocalTitle(position : Int, text:String){
        _localTitleList[position] = text
    }
}
