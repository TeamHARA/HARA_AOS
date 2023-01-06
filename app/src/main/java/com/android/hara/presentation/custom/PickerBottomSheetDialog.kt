package com.android.hara.presentation.custom

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.android.hara.R
import com.android.hara.databinding.FragmentBottomsheetPickerBinding
import com.android.hara.presentation.base.BindingNotDraggableBottomSheet


class PickerBottomSheetDialog :
    BindingNotDraggableBottomSheet<FragmentBottomsheetPickerBinding>(R.layout.fragment_bottomsheet_picker) {

    private val categoryList = listOf<String>("일상", "연애", "패션/뷰티", "커리어", "대학생", "진로고민", "메뉴고민")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pickerCategory.displayedValues = categoryList.toTypedArray()
        binding.pickerCategory.minValue = 0
        binding.pickerCategory.maxValue = categoryList.size-1
        binding.pickerCategory.displayedValues = categoryList.toTypedArray()

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

}