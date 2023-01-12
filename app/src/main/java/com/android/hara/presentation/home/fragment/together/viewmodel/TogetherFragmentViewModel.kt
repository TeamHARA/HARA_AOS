package com.android.hara.presentation.home.fragment.together.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TogetherFragmentViewModel @Inject constructor() : ViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success get() = _success

}