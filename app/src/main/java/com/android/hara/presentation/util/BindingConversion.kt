package com.android.hara.presentation.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.android.hara.R

@BindingAdapter("app:decide_selected")
fun AppCompatButton.selected(sel: Boolean) {
    if (sel) {
        this.background = this.context.getDrawable(R.drawable.shape_rectangle_orange1_fill_30)
        this.setTextColor(this.context.getColor(R.color.white))
    } else {
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
    } else {
        this.background = this.context.getDrawable(R.drawable.shape_rectanle_orange1_fill_4)
        this.setTextColor(this.context.getColor(R.color.white))
    }
}

/*
파라미터 두개 이상 넘기고 싶은 경우 확장함수가 아닌 이런 방식으로 사용
 */
@BindingAdapter(
    value = ["app:title_bold", "app:title_bold_start", "app:title_bold_end"],
    requireAll = true
)
fun setSpannableBold(textView: TextView, tilte: String, start: Int, end: Int) {
    val boldTypeface = Typeface.create(
        ResourcesCompat.getFont(textView.context, R.font.pretendard_bold),
        Typeface.NORMAL
    )
    val string = SpannableString(tilte)
    string.setSpan(TypefaceSpan(boldTypeface), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.text = string
}