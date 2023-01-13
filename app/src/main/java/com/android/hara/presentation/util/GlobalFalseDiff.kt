package com.android.hara.presentation.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class GlobalFalseDiff<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }
}