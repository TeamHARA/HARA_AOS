package com.android.hara.presentation.detail.decision

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.data.model.request.DecideAloneReqDto
import com.android.hara.data.model.request.DecideWithReqDto
import com.android.hara.domain.repository.HARARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DecideViewModel @Inject constructor(private val haraRepository: HARARepository) :
    ViewModel() {
    private val _enabled = MutableLiveData<Boolean>(false)
    val enabled get() = _enabled

    private val _decideSuccess = MutableLiveData<Boolean>(false)
    val decideSuccess get() = _decideSuccess

    fun decideWith(reqData: DecideWithReqDto) {
        Timber.e(reqData.toString())
        viewModelScope.launch {
            runCatching {
                haraRepository.patchDecideWith(reqData)
            }.onSuccess {
                if (it.status in 200..299) { // 내부 코드보면 응답코드 200~299를 의미
                    Timber.e("Success")
                    _decideSuccess.value = true
                } else { // 응답코드 400~599
                    Timber.e("Failure")
                    // TODO: TEST코드입니다. 추후 삭제
                    _decideSuccess.value = true
                }
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    fun decideAlone(reqData: DecideAloneReqDto) {
        viewModelScope.launch {
            runCatching {
                haraRepository.patchDecideAlone(reqData)
            }.onSuccess {
                if (it.status in 200..299) { // retrofit response의 it.successful을 보면 내부 코드보면 응답코드 200~299를 의미
                    Timber.e("Success")
                } else { // 응답코드 400~599
                    Timber.e("Failure")
                }
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
