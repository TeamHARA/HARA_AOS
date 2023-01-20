package com.android.hara.presentation.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.data.model.response.DetailWithResDto
import com.android.hara.domain.repository.HaraAloneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailWithViewModel @Inject constructor(private val haraAloneRepository: HaraAloneRepository) :
    ViewModel() {
    // selected number: 현재 선택된 옵션#를 저장, 아무 것도 안 선택돼있으면 0이 저장됨
    private val _sNum = MutableLiveData<Int>(0)
    val sNum get() = _sNum

    private val _success = MutableLiveData<Boolean>()
    val success get() = _success

    private val _detailDto = MutableLiveData<DetailWithResDto>()
    val detailDto get() = _detailDto

    fun getDetailWith(worryId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                haraAloneRepository.getDetailWith(worryId)
            }.onSuccess {
                if (it.status in 200..299) {
                    Timber.e(it.toString())
                    _detailDto.value = it // 넣어주는 시점 주의!
                    _success.value = true
                } else {
                    _success.value = false
                }
            }.onFailure {
                Timber.e(it)
                _success.value = false
            }
        }

    }
}