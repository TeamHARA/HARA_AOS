package com.android.hara.presentation.onesec.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OneSecViewModel @Inject constructor() : ViewModel() {
    private val _solution = MutableLiveData<String>()
    val solution get() = _solution

    private val _worry = MutableLiveData<String>()
    val worry get() = _worry
}