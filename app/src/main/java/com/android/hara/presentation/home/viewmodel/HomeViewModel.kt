package com.android.hara.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hara.data.model.request.RequestReqresUserDTO
import com.android.hara.domain.repository.HARARepository
import com.android.hara.domain.repository.ReqresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val ReqresRepository:ReqresRepository)
    :ViewModel(){

    //TODO 교체예정
    init {
            viewModelScope.launch {
                runCatching {
                    ReqresRepository.getReqres()
                 }.onSuccess {
                    if(it.isSuccessful) // 내부 코드보면 응답코드 200~299를 의미
                        Timber.e("Success")
                    else // 응답코드 400~599
                        Timber.e("Failure")
                }.onFailure {
                    Timber.e(it)
                }

                runCatching {
                    //ReqresRepository.postReqres(RequestReqresUserDTO("morpheus","leader"))
                    ReqresRepository.postReqres(RequestReqresUserDTO("morpheus","leader"))
                }.onSuccess {
                    if(it.isSuccessful) // 내부 코드보면 응답코드 200~299를 의미
                        Timber.e("Success")
                    else // 응답코드 400~599
                        Timber.e("Failure")
                }.onFailure { //데이터 없음 등등으로 인한 서버통신 자체가 실패한 경우
                    Timber.e(it)
                }

            }
        }
}