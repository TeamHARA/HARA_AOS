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
        binding.pickerCategory.displayedValues = categoryList.toTypedArray()
        binding.pickerCategory.minValue = 0
        binding.pickerCategory.maxValue = categoryList.size - 1
        binding.pickerCategory.displayedValues = categoryList.toTypedArray()
        binding.pickerCategory.wrapSelectorWheel = true //

        binding.pickerCategory.setOnValueChangedListener { picker, xoldVal, newVal ->
            Timber.e(picker.toString())
            Timber.e(xoldVal.toString())
            Timber.e(newVal.toString())
            //binding.pickerCategory.textColor = R.color.black
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