package com.android.hara.presentation.onesec

import android.os.Bundle
import com.android.hara.R
import com.android.hara.databinding.ActivityOneSecBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.util.setOnSingleClickListener

class OneSecActivity : BindingActivity<ActivityOneSecBinding>(R.layout.activity_one_sec) {
    private val solvedList = listOf<String>("어쩌구저쩌구","저쩌구어쩌구","블라블라블라")
    //TODO [보관함]에서 넘어와 [1초만에 고민해결하기] 부분 액티비티 입니다. 추후 네이밍 변경예정
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnPickSolve.setOnSingleClickListener {
            binding.ivSolvedCard.setImageResource(R.drawable.img_card_result)
            binding.tvOneSecSolution.text = ""
            binding.btnPickSolve.text = ""
        }

    }
}