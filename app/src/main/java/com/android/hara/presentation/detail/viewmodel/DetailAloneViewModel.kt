package com.android.hara.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hara.domain.repository.HARARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailAloneViewModel @Inject constructor(private val haraRepository: HARARepository) :
    ViewModel() {
    private val _sNum = MutableLiveData<Int>(0)
    val sNum get() = _sNum


}