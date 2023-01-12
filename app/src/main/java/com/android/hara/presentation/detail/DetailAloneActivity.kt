package com.android.hara.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityDetailAloneBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.detail.viewmodel.DetailAloneViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailAloneActivity :
    BindingActivity<ActivityDetailAloneBinding>(R.layout.activity_detail_alone) {

    private val detailAloneVm: DetailAloneViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding.appbarDetail.setNavigationOnClickListener {
            finish()
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