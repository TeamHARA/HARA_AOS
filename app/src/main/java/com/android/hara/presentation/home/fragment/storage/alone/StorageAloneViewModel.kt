package com.android.hara.presentation.home.fragment.storage.alone

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
class StorageAloneViewModel @Inject constructor(private val haraRepository: HARARepository) :
    ViewModel() {
    private val _aloneData = MutableLiveData<List<WorryListResDto.Data>>()
    val aloneData: LiveData<List<WorryListResDto.Data>> = _aloneData

    private val _isSolved = MutableLiveData<Int>(0)
    val isSolved get() = _isSolved

    fun getAloneList() {
        viewModelScope.launch {
            runCatching {
                haraRepository.getAloneList(_isSolved.value!!)
            }.onSuccess {
                if (it.isSuccessful) { // 내부 코드보면 응답코드 200~299를 의미
                    Timber.e("Success")
                    _aloneData.value = it.body()!!.data
                } else { // 응답코드 400~599
                    Timber.e("Failure")
                }
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
