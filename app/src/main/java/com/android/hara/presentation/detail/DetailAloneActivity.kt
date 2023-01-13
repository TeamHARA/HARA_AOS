package com.android.hara.presentation.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityDetailAloneBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.custom.EditBottomSheetDialog
import com.android.hara.presentation.detail.decision.FinalDecideActivity
import com.android.hara.presentation.detail.model.DecideData
import com.android.hara.presentation.detail.viewmodel.DetailAloneViewModel
import com.android.hara.presentation.home.fragment.together.DetailData
import com.android.hara.presentation.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailAloneActivity :
    BindingActivity<ActivityDetailAloneBinding>(R.layout.activity_detail_alone) {

    private val detailAloneVm: DetailAloneViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val worryData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("worryId", DetailData::class.java)
        } else {
            intent.getParcelableExtra("worryId")
        }
        detailAloneVm.getDetailAlone(worryData?.worryId ?: 0)
        binding.itMyPost = true
        binding.layoutOption1.tvOptPercent.visibility = View.GONE
        binding.layoutOption2.tvOptPercent.visibility = View.GONE
        binding.layoutOption3.tvOptPercent.visibility = View.GONE
        binding.layoutOption4.tvOptPercent.visibility = View.GONE

        binding.btnVote.setOnSingleClickListener {
            val res = detailAloneVm.detailDto.value!!.data
            var including = false // default: image X=
            val optionId = mutableListOf<Int>()
            val optionTitle = mutableListOf<String>()
            val optionPer = mutableListOf<Int?>()

            res.options.forEachIndexed { index, option ->
                if (option.hasImage) including = true
                optionId.add(option.id)
                optionTitle.add(option.title)
            }
            val decideData = DecideData(
                1,
                res.worryTitle,
                optionId,
                optionTitle,
                optionPer,
                true,
                including
            )

            startActivity(
                Intent(
                    this,
                    FinalDecideActivity::class.java
                ).putExtra("decideData", decideData)
            )
        }

        val bindingList = listOf(
            binding.layoutOption1,
            binding.layoutOption2,
            binding.layoutOption3,
            binding.layoutOption4
        )

        detailAloneVm.success.observe(this) {
            if (it) {
                binding.detailVm = detailAloneVm
                binding.category = detailAloneVm.detailDto.value!!.data.category
                detailAloneVm.detailDto.value!!.data.options.forEachIndexed { index, option ->
                    bindingList[index].root.visibility = View.VISIBLE // 선택지 갯수 만큼 visibilty 조절
                    if (option.hasImage) binding.flowImage.visibility =
                        View.VISIBLE // 하나라도 이미지 있다면 Flag발동
                    with(bindingList[index]) {
                        title = option.title
                        if (option.advantage == "") {
                            tvOptProTitle.visibility = View.GONE
                        } else {
                            advantage = option.advantage
                        }
                        if (option.disadvantage == "") {
                            tvOptConTitle.visibility = View.GONE
                        } else {
                            disadvantage = option.disadvantage
                        }
                    }
                }
            }
        }

        binding.appbarDetail.setNavigationOnClickListener {
            finish()
        }
        binding.ivEdit.setOnSingleClickListener {
            EditBottomSheetDialog().show(supportFragmentManager, "edit")
        }
        // n번째 옵션 클릭 시 옵션과 버튼 스타일 변하는 로직
        binding.layoutOption1.clOptBox.setOnClickListener {
            changeVmSnum(1)
        }
        binding.layoutOption2.clOptBox.setOnClickListener {
            changeVmSnum(2)
        }
        binding.layoutOption3.clOptBox.setOnClickListener {
            changeVmSnum(3)
        }
        binding.layoutOption4.clOptBox.setOnClickListener {
            changeVmSnum(4)
        }

        detailAloneVm.sNum.observe(this) {
            binding.detailVm = detailAloneVm
        }
    }

    // n번째 옵션이 선택되면 DetailViewModel 안의 sNum의 value가 n으로 바뀐다
    private fun changeVmSnum(n: Int) { // n이 선택된 상태
        // 1) n이 클릭되면: n만 비활성화돼야 해
        if (detailAloneVm.sNum.value == n) detailAloneVm.sNum.value = 0
        // 2) n이 클릭되면: n만 활성화돼야 해
        else detailAloneVm.sNum.value = n
    }
}