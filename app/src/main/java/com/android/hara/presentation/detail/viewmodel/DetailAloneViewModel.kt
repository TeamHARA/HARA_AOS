package com.android.hara.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.data.model.response.DetailAloneResDto
import com.android.hara.domain.repository.HARARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailAloneViewModel @Inject constructor(private val haraRepository: HARARepository) :
    ViewModel() {
    private val _sNum = MutableLiveData<Int>(0)
    val sNum get() = _sNum

    private val _success = MutableLiveData<Boolean>()
    val success get() = _success

    private val _detailDto = MutableLiveData<DetailAloneResDto>()
    val detailDto get() = _detailDto

    fun getDetailAlone(worryId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                haraRepository.getDetailAlone(worryId)
            }.onSuccess {
                if (it.isSuccessful) {
                    Timber.e(it.body().toString())
                    _detailDto.value = it.body() // 넣어주는 시점 주의!
                    _success.value = it.isSuccessful
                } else {
                    _success.value = it.isSuccessful
                }
            }.onFailure {
                Timber.e(it)
                _success.value = false
            }
        }

    }

}