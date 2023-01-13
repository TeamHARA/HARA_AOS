package com.android.hara.presentation.util

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import com.android.hara.R
import com.skydoves.progressview.ProgressView
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

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
            this.background =
                this.context.getDrawable(R.drawable.shape_rectangle_orange2_stroke_1_8)
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

// [홈화면/item_post.xml] optSelNum의 값에 따라 '옵션'의 체크버튼 스타일(visibility & src)이 바뀐다
@BindingAdapter(value = ["app:layMyPost", "app:layOptSelNum", "app:layOptSel", "app:layVoteOptSel"], requireAll = true)
fun optionCheckBtn(iv: ImageView, myPost: Boolean, optSelNum: Int, optSel: Boolean, voteOptSel: Boolean) {
    if (myPost) iv.visibility = View.GONE
    else {
        when (optSelNum) {
            -1 -> { // [투표 완] 그냥 체크 표시
                if (voteOptSel) { // 선택된 옵션
                    iv.visibility = View.VISIBLE
                    iv.setImageResource(R.drawable.ic_check)
                } else iv.visibility = View.GONE // 안 선택된 옵션
            }
            0 -> { // [투표 미완] 사용자가 옵션을 선택 안했으니 check off 표시
                iv.visibility = View.VISIBLE
                iv.setImageResource(R.drawable.ic_checkcircle_off)
            }
            else -> { // [투표 미완] 사용자가 옵션을 선택했으니 check on 표시
                iv.visibility = View.VISIBLE
                if (optSel) iv.setImageResource(R.drawable.ic_checkcircle_on)
                else iv.setImageResource(R.drawable.ic_checkcircle_off)
            }
        }
    }
}

// itOptSelNum == layOptSelNum == -1이면 투표 완료
// layOptSel = itOptSelNum에 따라 자기(옵션 뷰)가 선택됐으면 true
// layVoteOptSel = itVoteOptSel에 따라 자기 뷰(옵션 뷰)가 선택됐으면 true
@BindingAdapter(value = ["app:layOptSelNum", "app:layOptSel", "app:layVoteOptSel"], requireAll = true)
fun votedOptBackground(cl: ConstraintLayout, optSelNum: Int, optSel: Boolean, voteOptSel: Boolean) {
    if (optSelNum == -1) { // [투표 이미 완료]
        if (voteOptSel) // 선택된 옵션
            cl.background = cl.context.getDrawable(R.drawable.shape_rectangle_blue1_stroke_1_8)
        else // 선택 안 된 옵션
            cl.background = cl.context.getDrawable(R.drawable.shape_rectangle_gray4_stroke_1_8)
    }
    else { // [투표 아직 안 함]
        if (optSel) { // 선택된 옵션
            cl.background = cl.context.getDrawable(R.drawable.shape_rectangle_blue1_stroke_1_8)
        }
        else { // 선택 안 된 옵션
            cl.background = cl.context.getDrawable(R.drawable.shape_rectangle_gray4_stroke_1_8)
        }
    }
}

@BindingAdapter(value = ["app:layOptSelNum", "app:layOptSel", "app:layVoteOptSel"], requireAll = true)
fun votedOptTextColor(tv: TextView, optSelNum: Int, optSel: Boolean, voteOptSel: Boolean) {
    if (optSelNum == -1) { // [투표 이미 완료]
        if (voteOptSel) // 투표한 옵션
            tv.setTextColor(tv.context.getColor(R.color.blue_1))
        else // 투표 안 한 옵션
            tv.setTextColor(tv.context.getColor(R.color.gray_2))
    }
    else { // [투표 아직 안 함]
        if (optSel) { // 선택된 옵션
            tv.setTextColor(tv.context.getColor(R.color.blue_1))
        }
        else { // 선택 안 된 옵션
            tv.setTextColor(tv.context.getColor(R.color.black))
        }
    }
}

// 투표율 색깔 변경
@BindingAdapter(value = ["app:layOptSelNum", "app:layVoteOptSel"], requireAll = true)
fun optTurnoutColor(pv: ProgressView, optSelNum: Int, voteOptSel: Boolean) {
    if (optSelNum == -1) { // [투표 이미 완료]
        if (voteOptSel) // 투표한 옵션
            pv.highlightView.color = ContextCompat.getColor(pv.context, R.color.blue_3)
        else
            pv.highlightView.color = ContextCompat.getColor(pv.context, R.color.gray_4)
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

/* 파라미터 두개 이상 넘기고 싶은 경우 확장함수가 아닌 이런 방식으로 사용 */
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

@BindingAdapter("layoutMarginTop")
fun setLayoutMarginTop(view: View, dimen: Float) {
    view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
        topMargin = dimen.toInt()
    }
}

@BindingAdapter("app:perInt")
fun TextView.setPer(perInt: String?) {
    if (perInt != null) {
        this.text = "$perInt%"
    }
}

// [상세보기] 옵션 선택에 따라 check btn src가 달라진다
@BindingAdapter(value = ["app:layOptSelNum", "app:layOptSel", "app:layVoteOptSel", "app:layMyPost"], requireAll = true)
fun ImageView.changeCheckSrc(optSelNum: Int, optSel: Boolean, voteOptSel: Boolean, myPost: Boolean) {
    if (myPost) // [내 글]
        this.visibility = View.GONE
    else { // [남의 글]
        if (optSelNum == -1) { // [투표 완료]
            if (voteOptSel) { // 이 옵션에 투표함
                this.background = this.context.getDrawable(R.drawable.ic_check)
                this.visibility = View.VISIBLE
            } else { // 이 옵션에 투표 안 함
                this.visibility = View.GONE
            }
        } else if (optSelNum == 0) { // [투표 미완] 아무 옵션도 선택하지 않음
            this.background = this.context.getDrawable(R.drawable.ic_checkcircle_off)
            this.visibility = View.VISIBLE
        } else { // [투표 미완] 어떤 옵션이 선택됨
            if (optSel) { // 이 옵션이 선택됨
                this.background = this.context.getDrawable(R.drawable.ic_checkcircle_on)
                this.visibility = View.VISIBLE
            } else { // 이 옵션이 선택되지 않음
                this.background = this.context.getDrawable(R.drawable.ic_checkcircle_off)
                this.visibility = View.VISIBLE
            }
        }
    }
}



// [상세보기] 옵션 선택에 따라 옵션 text color가 달라진다
@BindingAdapter(value = ["app:layVoteOptSel", "app:layOptSel"], requireAll = true)
fun changeTextColor(tv: TextView, voteOptSel: Boolean, optSel: Boolean) {
    if (optSel) { // [투표 중] 옵션 선택
        tv.setTextColor(tv.context.getColor(R.color.blue_1))
    }
    else { // [투표 중은 아님]
        if (voteOptSel) { // [투표 완료] 이 옵션에 투표됐다
            tv.setTextColor(tv.context.getColor(R.color.blue_1))
        }
        else {
            tv.setTextColor(tv.context.getColor(R.color.black))
        }
    }
}

// [상세보기] 투표하기 버튼 스타일
@BindingAdapter(value = ["app:itOptSelNum", "app:itMyPost"], requireAll = true)
fun changeVoteBtnStyle(btn: AppCompatButton, optSelNum: Int, myPost: Boolean) {
    if (myPost) { // 1. [내 글]
        btn.background = btn.context.getDrawable(R.drawable.shape_rectangle_orange3_fill_8)
        btn.text = "최종결정 하러 가기"
        btn.setTextColor(btn.context.getColor(R.color.orange_1))
        btn.isEnabled = true
    }
    else { // 2. [남의 글]
        when (optSelNum) {
            -1 -> { // [투표 완료]
                btn.background = btn.context.getDrawable(R.drawable.shape_rectangle_gray3_fill_8)
                btn.text = "투표 완료!"
                btn.setTextColor(btn.context.getColor(R.color.white))
                btn.isEnabled = false
            }
            0 -> { // [투표 미완] 아무 옵션도 선택 X
                btn.background = btn.context.getDrawable(R.drawable.shape_rectangle_orange2_stroke_1_8)
                btn.text = "투표하기"
                btn.setTextColor(btn.context.getColor(R.color.orange_1))
                btn.isEnabled = false
            }
            else -> { // [투표 미완] 어떤 옵션이 선택됨
                btn.background = btn.context.getDrawable(R.drawable.shape_rectangle_orange3_fill_8)
                btn.text = "투표하기"
                btn.setTextColor(btn.context.getColor(R.color.orange_1))
                btn.isEnabled = true
            }
        }
    }
}
