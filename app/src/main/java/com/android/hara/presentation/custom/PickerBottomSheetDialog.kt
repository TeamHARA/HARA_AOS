package com.android.hara.presentation.custom

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.android.hara.R
import com.android.hara.databinding.FragmentBottomsheetPickerBinding
import com.android.hara.presentation.base.BindingNotDraggableBottomSheet
import com.android.hara.presentation.util.HARAobjcet.categoryList
import timber.log.Timber


class PickerBottomSheetDialog :
    BindingNotDraggableBottomSheet<FragmentBottomsheetPickerBinding>(R.layout.fragment_bottomsheet_picker) {

    private var count = 0

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setOnClickListener()
    }

    private fun init() {
        binding.pickerCategory.displayedValues = categoryList.toTypedArray()
        binding.pickerCategory.minValue = 0
        binding.pickerCategory.maxValue = categoryList.size - 1
        binding.pickerCategory.displayedValues = categoryList.toTypedArray()
        binding.pickerCategory.wrapSelectorWheel = true //
    }

    private fun setOnClickListener() {
        binding.pickerCategory.setOnValueChangedListener { picker, xoldVal, newVal ->
            count = newVal
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnComplete.setOnClickListener {
            Timber.e(count.toString())
        }
    }
}