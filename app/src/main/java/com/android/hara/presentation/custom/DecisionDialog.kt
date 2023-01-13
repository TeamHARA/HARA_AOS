package com.android.hara.presentation.custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import com.android.hara.databinding.DialogDecisionBinding
import com.android.hara.presentation.custom.model.DialogData
import com.android.hara.presentation.util.dpToPx
import com.android.hara.presentation.util.setOnSingleClickListener

class DecisionDialog(
    val context: Context,
    val data: DialogData,
    private val itemActionListener: () -> Unit,
) {
    private val inflater by lazy { LayoutInflater.from(context) }
    private val binding: DialogDecisionBinding = DialogDecisionBinding.inflate(inflater)
    private lateinit var dialog: Dialog

    init {
        with(binding) {
            this.title = data.title
            this.warn = data.warn
            this.action = data.action
            this.cancel = data.cancel
        }
    }

    fun showDialog() {
        dialog = Dialog(context)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(binding.root)
            val params: WindowManager.LayoutParams = dialog.window!!.attributes
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 배경색 투명

            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            (binding.root.layoutParams as ViewGroup.MarginLayoutParams).apply {
                marginStart = 12.dpToPx(binding.root.context)
                marginEnd = 12.dpToPx(binding.root.context)
            } // 레이아웃 속성 재 적용
            dialog.window!!.attributes = params
        }
        setListener()
        dialog.show()
    }

    private fun setListener() {
        binding.btnDialogCancle.setOnSingleClickListener {
            dialog.cancel()
        }
        binding.btnDialogAction.setOnSingleClickListener {
            itemActionListener()
            dialog.cancel()
        }
    }
}