package com.android.hara.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.android.hara.domain.repository.HARARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val HARARepository:HARARepository)
    :ViewModel(){
}