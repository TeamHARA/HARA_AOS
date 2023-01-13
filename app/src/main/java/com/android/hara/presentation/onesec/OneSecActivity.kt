package com.android.hara.presentation.onesec

import android.os.Bundle
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityOneSecBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.onesec.adapter.OneSecAdapter
import com.android.hara.presentation.onesec.viewmodel.OneSecViewModel
import com.android.hara.presentation.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneSecActivity : BindingActivity<ActivityOneSecBinding>(R.layout.activity_one_sec) {

    private val onesecViewModel: OneSecViewModel by viewModels()
    private val oneSecAdapter = OneSecAdapter() { worryTitle ->
        onClickLastWorry(worryTitle)
//        binding.etSearch.setText(worryTitle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        addObserve()
        setOnClickListener()
    }

    private fun onClickLastWorry(worryTitle: String) {
        onesecViewModel.worry.value = worryTitle
        binding.ivSolvedCard.setImageResource(R.drawable.img_one_sec) // 텍스트 변경 시 활성화 해제
        onesecViewModel.solution.value = null
    }

    private fun init() {
        binding.viewmodel = onesecViewModel
        binding.rcvLastWorry.adapter = oneSecAdapter
        onesecViewModel.getLastWorry()
    }

    private fun setOnClickListener() {
        binding.appbarDetail.setNavigationOnClickListener {
            finish()
        }
        binding.btnPickSolve.setOnSingleClickListener {
            onesecViewModel.getRandom()
            binding.ivSolvedCard.setImageResource(R.drawable.img_card_result)
        }
    }

    private fun addObserve() {
        onesecViewModel.worry.observe(this) {
            binding.btnPickSolve.isEnabled = it.isNotEmpty()
        }
        onesecViewModel.lastWorryList.observe(this) { lastWorryList ->
            oneSecAdapter.submitList(lastWorryList)
        }
    }
}
