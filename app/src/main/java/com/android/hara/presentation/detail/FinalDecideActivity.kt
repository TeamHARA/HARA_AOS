package com.android.hara.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityFinalDecideBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.detail.viewmodel.DecideViewModel

class FinalDecideActivity :
    BindingActivity<ActivityFinalDecideBinding>(R.layout.activity_final_decide) {
    // FinalDecideFragment에서 버튼을 다스릴 애가 local임.
    // local이 data binding으로 연결된 fragment_final_decide.xml에 있는 selected라는 변수로 전달됨.
    // .xml에서 selected가 버튼에 달려있어.
    // .xml에서 selected가 app.어쩌고에 연결돼있어.
    // util/BindingConversion.kt에 보면 app. 어쩌고라고 써있었음.
    // 그 .kt 파일에 보면 sel이 있어.
    // sel에 최종적으로 selected가 전달되는 것
    // [결론] Fragment의 local = .xml의 selected = BindingConversion.kt의 sel

    //var local = true
    private val decisionViewModel by viewModels<DecideViewModel>()
    private var count = 4

    private var selectListBool = mutableListOf<Boolean>(false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (count == 2) {
            binding.clOpt3.visibility = View.VISIBLE
            binding.clOpt4.visibility = View.VISIBLE
        } else if (count == 3) {
            binding.clOpt4.visibility = View.VISIBLE
        }

        // [버튼 클릭] 옵션 버튼이 클릭되면 뷰모델 내 selectedN의 값을 true로 바꾼다
        binding.clOpt1.setOnClickListener {
            checkAndSetSelected(decisionViewModel, 0)
        }
        binding.clOpt2.setOnClickListener {
            checkAndSetSelected(decisionViewModel, 1)
        }
        binding.clOpt3.setOnClickListener {
            checkAndSetSelected(decisionViewModel, 2)
        }
        binding.clOpt4.setOnClickListener {
            checkAndSetSelected(decisionViewModel, 3)
        }

        // [observer] 뷰모델 내 selectedN의 값이 바뀌었는지 감지한다
        observeSelected(decisionViewModel)

        binding.btnFinalDecideLetssolve.setOnClickListener {
            val intent = Intent(this, FinalResolveActivity::class.java)
            startActivity(intent)
        }

        binding.btnFinalDecideClose.setOnClickListener {
            finish()
        }

    }

    private fun checkAndSetSelected(vm: DecideViewModel, n: Int) {
        // 옵션이 하나라도 true라면
        if (selectListBool.contains(true)) {
            if (selectListBool[n]) {
                selectListBool[n] = false
                decisionViewModel.enabled.value = false
                changeOptStyle(n, false)
            } else {
                selectListBool.replaceAll { false }
                selectListBool.forEachIndexed { index, _ ->
                    changeOptStyle(index,false)
                }
                selectListBool[n] = true
                decisionViewModel.enabled.value = true
                changeOptStyle(n, true)
            }
        }
        // 옵션이 다 false라면
        else {
            selectListBool[n] = true
            decisionViewModel.enabled.value = true
            changeOptStyle(n, true)
        }
    }


    private fun observeSelected(vm: DecideViewModel) {
        decisionViewModel.enabled.observe(this){
            binding.selected = it
            binding.btnFinalDecideLetssolve.isEnabled = it
        }
    }

    private fun changeOptStyle(n: Int, b: Boolean) {
        if (b) {
            when (n) {
                0 -> {
                    binding.clOpt1.background =
                        getDrawable(R.drawable.shape_rectangle_blue1_fill_8)
                    binding.tvOpt1Content.setTextColor(getColor(R.color.white))
                    binding.tvOpt1Num.visibility = View.GONE
                }
                1 -> {
                    binding.clOpt2.background =
                        getDrawable(R.drawable.shape_rectangle_blue1_fill_8)
                    binding.tvOpt2Content.setTextColor(getColor(R.color.white))
                    binding.tvOpt2Num.visibility = View.GONE
                }
                2 -> {
                    binding.clOpt3.background =
                        getDrawable(R.drawable.shape_rectangle_blue1_fill_8)
                    binding.tvOpt3Content.setTextColor(getColor(R.color.white))
                    binding.tvOpt3Num.visibility = View.GONE
                }
                3 -> {
                    binding.clOpt4.background =
                        getDrawable(R.drawable.shape_rectangle_blue1_fill_8)
                    binding.tvOpt4Content.setTextColor(getColor(R.color.white))
                    binding.tvOpt4Num.visibility = View.GONE
                }
            }
        }
        else {
            when (n) {
                0 -> {
                    binding.clOpt1.background =
                        getDrawable(R.drawable.shape_rectangle_blue3_stroke_1_8)
                    binding.tvOpt1Content.setTextColor(getColor(R.color.gray_2))
                    binding.tvOpt1Num.visibility = View.VISIBLE
                }
                1 -> {
                    binding.clOpt2.background =
                        getDrawable(R.drawable.shape_rectangle_blue3_stroke_1_8)
                    binding.tvOpt2Content.setTextColor(getColor(R.color.gray_2))
                    binding.tvOpt2Num.visibility = View.VISIBLE
                }
                2 -> {
                    binding.clOpt3.background =
                        getDrawable(R.drawable.shape_rectangle_blue3_stroke_1_8)
                    binding.tvOpt3Content.setTextColor(getColor(R.color.gray_2))
                    binding.tvOpt3Num.visibility = View.VISIBLE
                }
                3 -> {
                    binding.clOpt4.background =
                        getDrawable(R.drawable.shape_rectangle_blue3_stroke_1_8)
                    binding.tvOpt4Content.setTextColor(getColor(R.color.gray_2))
                    binding.tvOpt4Num.visibility = View.VISIBLE
                }
            }
        }
    }
}