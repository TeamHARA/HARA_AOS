package com.android.hara.presentation.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
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

@BindingAdapter("app:storage_flag")
fun TextView.xmlIng(still: Boolean) {
    if (still) {
        this.background = this.context.getDrawable(R.drawable.shape_rectangle_orange2_stroke_1_4)
        this.setTextColor(this.context.getColor(R.color.orange_2))
        // 고민중이라는 문구
    }
    else {
        this.background = this.context.getDrawable(R.drawable.shape_rectanle_orange1_fill_4)
        this.setTextColor(this.context.getColor(R.color.white))
    }
}

@BindingAdapter("app:your_post_vote")
fun AppCompatButton.optSelNum(n: Int) {
    when (n) {
        -1 -> { // [투표 완] 이미 투표 완료 상태
            this.background = this.context.getDrawable(R.drawable.shape_rectangle_gray3_fill_8)
            this.setTextColor(this.context.getColor(R.color.white))
            this.text = "투표 완료!"
            this.isEnabled = false
        }
        0 -> { // [투표 미완] 옵션이 아직 선택되지 않음
            this.background = this.context.getDrawable(R.drawable.shape_rectangle_orange2_stroke_1_8)
            this.setTextColor(this.context.getColor(R.color.orange_1))
            this.text = "투표하기"
            this.isEnabled = true
        }
        else -> { // [투표 미완] 옵션이 선택된 상태
            this.background = this.context.getDrawable(R.drawable.shape_rectangle_orange3_fill_8)
            this.setTextColor(this.context.getColor(R.color.orange_1))
            this.text = "투표하기"
            this.isEnabled = true
        }
    }
}