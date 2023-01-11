package com.android.hara.presentation.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.android.hara.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

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

// [홈화면/item_post.xml] optSelNum의 값에 따라 '투표하기' 버튼의 스타일이 바뀐다
@BindingAdapter("app:vote_btn_style")
fun AppCompatButton.itOptSelNum(n: Int) {
    when (n) {
        -1 -> { // [투표 완] 이미 투표 완료 상태
            this.isEnabled = false
            this.background = this.context.getDrawable(R.drawable.shape_rectangle_gray3_fill_8)
            this.setTextColor(this.context.getColor(R.color.white))
            this.text = "투표 완료!"
        }
        0 -> { // [투표 미완] 사용자가 옵션을 선택 안 함
            this.isEnabled = true
            this.background = this.context.getDrawable(R.drawable.shape_rectangle_orange2_stroke_1_8)
            this.setTextColor(this.context.getColor(R.color.orange_1))
            this.text = "투표하기"
        }
        else -> { // [투표 미완] 사용자가 옵션을 선택함
            this.isEnabled = true
            this.background = this.context.getDrawable(R.drawable.shape_rectangle_orange3_fill_8)
            this.setTextColor(this.context.getColor(R.color.orange_1))
            this.text = "투표하기"
        }
    }
}

// [홈화면/item_post.xml] optSelNum의 값에 따라 '옵션'의 체크버튼 스타일이 바뀐다
@BindingAdapter(value = ["app:layOptSelNum", "app:layOptSel", "app:layVoteOptSel"], requireAll = true)
fun optionCheckBtn(imageView: ImageView, n: Int, b: Boolean, voteBool: Boolean) {
    when (n) {
        -1 -> { // [투표 완] 그냥 체크 표시
            if (voteBool) imageView.setImageResource(R.drawable.ic_check)
            else imageView.visibility = View.GONE
        }
        0 -> { // [투표 미완] 사용자가 옵션을 선택 안했으니 check off 표시
            imageView.setImageResource(R.drawable.ic_checkcircle_off)
        }
        else -> { // [투표 미완] 사용자가 옵션을 선택했으니 check on 표시
            if (b) imageView.setImageResource(R.drawable.ic_checkcircle_on)
            else imageView.setImageResource(R.drawable.ic_checkcircle_off)
        }
    }
}

@BindingAdapter("app:category_id")
fun TextView.setCategory(categoryId: Int) {
    this.text = resources.getStringArray(R.array.category_array)[categoryId]
}

@BindingAdapter("app:date_text")
fun TextView.setDate(dateText: String) {
    this.text = SimpleDateFormat(
        "yyyy.MM.dd",
        Locale("ko", "KR")
    ).format(Date.from(Instant.parse(dateText)))
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