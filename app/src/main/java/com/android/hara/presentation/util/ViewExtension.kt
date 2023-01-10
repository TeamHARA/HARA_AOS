package com.android.hara.presentation.util

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.android.hara.R
import com.google.android.material.snackbar.Snackbar

fun View.makeSnackbar(messgae: String) {
    Snackbar.make(
        this,
        messgae, Snackbar.LENGTH_SHORT
    ).show()
}

fun View.makeToast(messgae: String) {
    Toast.makeText(
        this.context, messgae, Toast.LENGTH_SHORT
    ).show()
}

fun View.setOnSingleClickListener(onSingleClick: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener { onSingleClick(it) })
}

fun Context.stringOf(@StringRes resId: Int) = getString(resId)

fun Context.colorOf(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun Context.drawableOf(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)

fun Int.dpToPx(context: Context): Int {
    return context.resources.displayMetrics.density.let { density ->
        (this * density).toInt()
    }
}

fun Int.pxToDp(context: Context): Int {
    return context.resources.displayMetrics.density.let { density ->
        (this / density).toInt()
    }
}

fun TextView.setBold(context: Context, text: String, start: Int, end: Int) {
    val mediumTypeface = Typeface.create(
        ResourcesCompat.getFont(context, R.font.pretendard_medium),
        Typeface.NORMAL
    )
    val boldTypeface = Typeface.create(
        ResourcesCompat.getFont(context, R.font.pretendard_bold),
        Typeface.NORMAL
    )
    val string = SpannableString(R.string.write_title_question.toString())
    string.setSpan(TypefaceSpan(mediumTypeface), 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    string.setSpan(TypefaceSpan(boldTypeface), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.text = text
}