package com.android.hara.presentation.home.fragment.storage.alone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.data.model.response.WorryListResDto
import com.android.hara.domain.repository.HaraAloneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StorageAloneViewModel @Inject constructor(private val haraAloneRepository: HaraAloneRepository) :
    ViewModel() {
    private val _aloneData = MutableLiveData<List<WorryListResDto.Data>>()
    val aloneData: LiveData<List<WorryListResDto.Data>> = _aloneData

    private val _isSolved = MutableLiveData<Int>(0)
    val isSolved get() = _isSolved

    init {
        getAloneList()
    }

    fun getAloneList() {
        viewModelScope.launch {
            runCatching {
                haraAloneRepository.getAloneList(_isSolved.value ?: 0)
            }.onSuccess {
                if (it.status in 200..299) { // 내부 코드보면 응답코드 200~299를 의미
                    Timber.e("Success")
                    _aloneData.value = it.data ?: listOf() // null로 터지는 걸 막아준다.
                } else { // 응답코드 400~599
                    Timber.e("Failure")
                }
            }.onFailure {
                Timber.e(it)
            }
        }
    }
}
