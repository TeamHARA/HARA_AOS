package com.android.hara.presentation.write.fragment.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryFregViewModel @Inject constructor() : ViewModel() {
    private val _category = MutableLiveData<Int>()
    val category get() = _category

    fun setCategory(category:Int){
        _category.value = category
    }
}
