package com.android.hara.presentation.detail.decision

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.data.model.request.DecideAloneReqDto
import com.android.hara.data.model.request.DecideWithReqDto
import com.android.hara.databinding.ActivityFinalDecideBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.detail.model.DecideData
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
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

    private val decisionViewModel by viewModels<DecideViewModel>()

    private var selectListBool = mutableListOf<Boolean>(false, false, false, false)
    private var selOptId: Int? = null // 프론트 기준 - 선택된 option Num

    // 1, 2, 3, 4번째 옵션 뷰가 선택(클릭)됐는지를 저장. n+1번째 옵션 뷰가 선택됐으면 [n]이 true
    private lateinit var decideData: DecideData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // data class 받아오기
        // worryId, worryTitle, optionId, optionTitle, isAlone(commentCount 있으면 false)
        if (intent.hasExtra("decideData")) {
            decideData =
                if (android.os.Build.VERSION.SDK_INT >= 33) intent.getParcelableExtra(
                    "decideData",
                    DecideData::class.java
                )!!
                else intent.getParcelableExtra("decideData")!!
            // 옵션 개수에 따라 그 개수의 옵션 뷰가 보이게
            setOptionListView()
        } else Timber.e("전달된 intent가 없습니다.")

        binding.decideData = decideData

        // [observer] 뷰모델 내 selectedN의 값이 바뀌었는지 감지한다
        addObserve()
        onClickOptionBtn()
        onClickSolveBtn()
        onClickCloseBtn()
    }

    private fun setOptionListView() {
        when (decideData.optionId.size) {
            2 -> {
                binding.clOpt3.visibility = View.INVISIBLE
                binding.clOpt4.visibility = View.INVISIBLE
            }
            3 -> {
                binding.clOpt4.visibility = View.INVISIBLE
            }
        }
    }

    private fun onClickCloseBtn() {
        binding.btnFinalDecideClose.setOnClickListener {
            finish()
        }
    }

    private fun onClickOptionBtn() {
        // [버튼 클릭] 옵션 버튼이 클릭되면 뷰모델 내 selectedN의 값을 true로 바꾼다
        binding.clOpt1.setOnClickListener {
            checkAndSetSelected(0)
        }
        binding.clOpt2.setOnClickListener {
            checkAndSetSelected(1)
        }
        binding.clOpt3.setOnClickListener {
            checkAndSetSelected(2)
        }
        binding.clOpt4.setOnClickListener {
            checkAndSetSelected(3)
        }
    }

    private fun onClickSolveBtn() {
        binding.btnFinalDecideLetssolve.setOnClickListener {
            selOptId = selectListBool.indexOf(true)
            if (decideData.isAlone) {
                decisionViewModel.decideAlone(
                    DecideAloneReqDto(
                        decideData.worryId,
                        decideData.optionId[selOptId!!]
                    )
                )
            } else {
                decisionViewModel.decideWith(
                    DecideWithReqDto(
                        decideData.worryId,
                        decideData.optionId[selOptId!!]
                    )
                )
            }
        }
    }

    private fun checkAndSetSelected(n: Int) { // n+1번째 옵션이 선택됐다
        // 1) 옵션이 하나라도 true라면
        if (selectListBool.contains(true)) {
            // 1-1) 그 옵션이 이미 true였다면 false로 바꿔줘야 한다
            if (selectListBool[n]) {
                selectListBool[n] = false
                decisionViewModel.enabled.value = false // n+1번째 옵션이 다시 선택됐으니 버튼이 비활성화돼야 한다
                changeOptStyle(n, false) // n+1번째 옵션 뷰를 'selected 스타일'로 바꿔준다
            }
            // 1-2) 어떤 옵션이 true인데, 또다른 옵션(n+1번째)을 선택한 거라면,
            // 그 옵션 뷰의 스타일이 그 옵션 뷰가 선택된 것처럼 바뀌어야 한다
            else {
                selectListBool.replaceAll { false } // 모든 옵션에 대해 일단 false로 바꿔준다
                selectListBool.forEachIndexed { index, _ ->
                    changeOptStyle(index, false)
                } // 역시 모든 옵션 뷰에 대해 스타일을 안 선택된 것처럼 바꿔준다
                selectListBool[n] = true // n+1번째 옵션이 선택된 것이니 그것을 true로 바꿔준다
                decisionViewModel.enabled.value = true // 버튼은 활성화가 돼야 한다
                changeOptStyle(n, true) // n+1번째 옵션 뷰를 'selected 스타일'로 바꿔준다
            }
        }
        // 2) 옵션이 모두 false라면
        else {
            selectListBool[n] = true
            decisionViewModel.enabled.value = true
            changeOptStyle(n, true)
        }
    }

    private fun addObserve() {
        // 뷰모델 내 enabled가 true가 되면
        // (데이터 바인딩된) .xml 내 selected 변수를 true로 설정하고 버튼을 활성화시킨다
        decisionViewModel.enabled.observe(this) {
            // it: 뷰모델 내 enabled의 값
            binding.selected = it
            binding.btnFinalDecideLetssolve.isEnabled = it
        }

        decisionViewModel.decideSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                val intent = Intent(this, FinalResolveActivity::class.java).apply {
                    putExtra("worryTitle", decideData.worryTitle)
                    putExtra("selOptTitle", decideData.optionTitle[selOptId!!])
                    putExtra("includeImg", decideData.includeImg)
                    putExtra("optionNum", selOptId!!)
                }
                startActivity(intent)
            }
        }
    }

    // 옵션 뷰 배경/텍스트컬러를 바꿔준다
    private fun changeOptStyle(n: Int, b: Boolean) {
        if (b) { // n+1번째 옵션 뷰의 배경/글자changeOptStyle색 등을 'selected' 스타일로 바꿔준다
            when (n) {
                0 -> {
                    binding.clOpt1.background = getDrawable(R.drawable.shape_rectangle_blue1_fill_8)
                    binding.tvOpt1Content.setTextColor(getColor(R.color.white))
                    binding.tvOpt1Num.visibility = View.GONE
                    setOption1Img()
                }
                1 -> {
                    binding.clOpt2.background = getDrawable(R.drawable.shape_rectangle_blue1_fill_8)
                    binding.tvOpt2Content.setTextColor(getColor(R.color.white))
                    binding.tvOpt2Num.visibility = View.GONE
                    setOption2Img()
                }
                2 -> {
                    binding.clOpt3.background = getDrawable(R.drawable.shape_rectangle_blue1_fill_8)
                    binding.tvOpt3Content.setTextColor(getColor(R.color.white))
                    binding.tvOpt3Num.visibility = View.GONE
                    setNothing()
                }
                3 -> {
                    binding.clOpt4.background = getDrawable(R.drawable.shape_rectangle_blue1_fill_8)
                    binding.tvOpt4Content.setTextColor(getColor(R.color.white))
                    binding.tvOpt4Num.visibility = View.GONE
                    setNothing()
                }
            }
        } else { // n+1번째 옵션 뷰의 배경/글자색 등을 'not selected' 스타일로 바꿔준다
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
            setNothing()
        }
    }

    private fun setNothing() {
        binding.imgFinalDecide1.root.alpha = 0.3F
        binding.imgFinalDecide2.root.alpha = 0.3F
    }

    private fun setOption1Img() {
        binding.imgFinalDecide1.root.alpha = 1F
        binding.imgFinalDecide2.root.alpha = 0.3F
    }

    private fun setOption2Img() {
        binding.imgFinalDecide2.root.alpha = 1F
        binding.imgFinalDecide1.root.alpha = 0.3F
    }
}
