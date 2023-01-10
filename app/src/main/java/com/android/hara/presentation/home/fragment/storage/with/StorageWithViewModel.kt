package com.android.hara.presentation.home.fragment.storage.with

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.data.model.response.WorryListResDto
import com.android.hara.domain.repository.HARARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StorageWithViewModel @Inject constructor(private val haraRepository: HARARepository) :
    ViewModel() {
    private val _withData = MutableLiveData<List<WorryListResDto.Data>>()
    val withData: LiveData<List<WorryListResDto.Data>> = _withData

    private val _isSolved = MutableLiveData<Int>(0)
    val isSolved: LiveData<Int> = _isSolved

    fun getWithList(isSolved: Int) {
        viewModelScope.launch {
            runCatching {
                haraRepository.getWithList(_isSolved.value!!)
            }.onSuccess {
                if (it.isSuccessful) { // 내부 코드보면 응답코드 200~299를 의미
                    Timber.e("Success")
                    _withData.value = it.body()!!.data
                } else { // 응답코드 400~599
                    Timber.e("Failure")
                }
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
