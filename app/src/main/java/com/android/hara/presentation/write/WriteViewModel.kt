package com.android.hara.presentation.write

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.data.model.request.Option
import com.android.hara.data.model.request.WorryAloneRequestDto
import com.android.hara.data.model.request.WorryWithRequestDto
import com.android.hara.domain.repository.HARARepository
import com.android.hara.presentation.write.fragment.proscons.model.PronsData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(private val haraRepository: HARARepository) : ViewModel() {
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress


    var title = "" // 제목 1번
    var content = "" // 내용 1번
    private val _titleList = mutableListOf<String>("", "", "", "") // 선택지 2번
    val titleList get() = _titleList
    private val _pronsList = mutableListOf<PronsData>() // 장단점 리스트 3번
    val pronsList get() = _pronsList
    var categoty = -1 // 카테고리 4번
    private var isWith = false // 혼자고민 함께고민 True면 함께 false면 혼자
    private val optionList = mutableListOf<Option>()

    init {
        _progress.value = 1
        titleList.forEachIndexed { index, s ->
            if (s != "") { // 유효한 선택지 만큼 optionData를 생성
                optionList.add(
                    Option(
                        pronsList[index].advantage,
                        pronsList[index].disadvantage,
                        false,
                        "",
                        titleList[index]
                    )
                )
            }
        }
    }

    fun postWorry() {
        if (isWith)
            viewModelScope.launch {
                kotlin.runCatching {
                    haraRepository.postWorryWith(
                        WorryWithRequestDto(
                            categoryId = categoty,
                            commentOn = false,
                            content = content,
                            options = optionList,
                            title = title
                        )
                    )
                }.onSuccess {

                }.onFailure {
                    Timber.e(it)
                }
            } else {
            viewModelScope.launch {
                kotlin.runCatching {
                    haraRepository.postWorryAlone(
                        WorryAloneRequestDto(
                            categoryId = categoty,
                            content = content,
                            options = optionList,
                            title = title
                        )
                    )
                }.onSuccess {

                }.onFailure {
                    Timber.e(it)
                }
            }
        }
    }

    fun addProgress() {
        // viewModel은 (=) 사용해서 변경해야 함!!!!! ( _progress.value?.plus(-1) 에러 )
        _progress.value = _progress.value!! + 1
    }

    fun subProgress() {
        _progress.value = _progress.value!! - 1
    }
}
