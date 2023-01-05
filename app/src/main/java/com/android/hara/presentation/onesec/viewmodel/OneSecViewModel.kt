package com.android.hara.presentation.onesec.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OneSecViewModel : ViewModel() {
    private val _enabled = MutableLiveData<Boolean>(false)
    val enabled get() = _enabled

//    fun textChanged(){
//        if()
//    }


}