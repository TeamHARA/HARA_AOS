package com.android.hara.presentation.onesec

import android.os.Bundle
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityOneSecBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.onesec.adapter.OneSecAdapter
import com.android.hara.presentation.onesec.model.WorryData
import com.android.hara.presentation.onesec.viewmodel.OneSecViewModel
import com.android.hara.presentation.util.setOnSingleClickListener

class OneSecActivity : BindingActivity<ActivityOneSecBinding>(R.layout.activity_one_sec) {

    private val onesecViewModel: OneSecViewModel by viewModels()
    private val oneSecAdapter = OneSecAdapter()
    private val lastworrise = listOf<WorryData>(
        WorryData("2022.12.12", "asdfsdfasdfsdaf"),
        WorryData("2022.12.13", "asdfsdfasdfsdaf"),
        WorryData("2022.12.14", "asdfsdfasdfsdaf"),
        WorryData("2022.12.15", "asdfsdfasdfsdaf"),
        WorryData("2022.12.16", "asdfsdfasdfsdaf"),
        WorryData("2022.12.17", "asdfsdfasdfsdaf"),
        WorryData("2022.12.18", "asdfsdfasdfsdaf"),
        WorryData("2022.12.19", "asdfsdfasdfsdaf"),
        WorryData("2022.12.20", "asdfsdfasdfsdaf"),
        WorryData("2022.12.21", "asdfsdfasdfsdaf")
    )
    private val solvedList =
        listOf("가", "나", "다", "라", "마", "바", "사", "아", "자", "차", "카", "타", "파", "하")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        addObserve()
        setOnClickListener()
    }

    private fun init() {
        binding.viewmodel = onesecViewModel
        binding.rcvLastWorry.adapter = oneSecAdapter
        oneSecAdapter.submitList(lastworrise.toList())
    }

    private fun setOnClickListener() {
        binding.appbarDetail.setNavigationOnClickListener {
            finish()
        }
        binding.btnPickSolve.setOnSingleClickListener {
            binding.ivSolvedCard.setImageResource(R.drawable.img_card_result)
            onesecViewModel.solution.value = solvedList[(solvedList.indices).random()]
        }
    }

    private fun addObserve() {
        onesecViewModel.worry.observe(this) {
            binding.btnPickSolve.isEnabled = it.isNotEmpty()
        }
    }
}