package com.android.hara.presentation.detail.viewmodel

import com.android.hara.domain.repository.HARARepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailWithViewModel @Inject constructor(private val haraRepository: HARARepository) {

}