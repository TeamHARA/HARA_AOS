package com.android.hara.presentation.util

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.android.hara.R

@BindingAdapter("app:decide_selected")
fun AppCompatButton.selected(sel: Boolean) {
        if (sel) {
        this.background = this.context.getDrawable(R.drawable.shape_rectangle_orange1_fill_30)
        this.setTextColor(this.context.getColor(R.color.white))
    }
    else {
        this.background = this.context.getDrawable(R.drawable.shape_rectangle_blue3_stroke_1_30)
        this.setTextColor(this.context.getColor(R.color.gray_2))
    }
}