package com.android.hara.presentation.onesec.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.domain.repository.HARARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OneSecViewModel @Inject constructor(private val haraRepository: HARARepository) :
    ViewModel() {
    private val _solution = MutableLiveData<String>()
    val solution get() = _solution

    private val _worry = MutableLiveData<String>()
    val worry get() = _worry

    fun getRandom() {
        viewModelScope.launch {
            runCatching {
                haraRepository.getRandom()
            }.onSuccess {
                if (it.isSuccessful) { // 내부 코드보면 응답코드 200~299를 의미
                    Timber.e("Success")
                    _solution.value = it.body()?.data?.content
                } else { // 응답코드 400~599
                    Timber.e("Failure")
                }
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
