package com.android.hara.presentation.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
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

fun Context.stringOf(@StringRes resId: Int) = getString(resId)

fun Context.colorOf(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun Context.drawableOf(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)
