package com.android.hara.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityDetailWithBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.custom.DecisionDialog
import com.android.hara.presentation.custom.model.DialogData
import com.android.hara.presentation.detail.viewmodel.DetailViewWithModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailWithActivity :
    BindingActivity<ActivityDetailWithBinding>(R.layout.activity_detail_with) {
    //TODO [고민글 상세보기] 부분입니다. 추후 네이밍 변경 예정

    private val detailVm by viewModels<DetailViewWithModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val worryId = intent.getIntExtra("worryId", 0)
        //detailVm.

        binding.appbarDetail.setNavigationOnClickListener {
            finish()
        }
        binding.tvDetailCategory.setOnClickListener {
            DecisionDialog(
                this, DialogData(
                    getString(R.string.anonymity),
                    getString(R.string.anonymity),
                    getString(R.string.anonymity),
                    getString(R.string.anonymity)
                )
            ) {}.showDialog()
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

        detailVm.sNum.observe(this) {
            binding.detailVm = detailVm
        }
    }

    // n번째 옵션이 선택되면 DetailViewModel 안의 sNum의 value가 n으로 바뀐다
    private fun changeVmSnum(n: Int) { // n이 선택된 상태
        // 1) n이 클릭되면: n만 비활성화돼야 해
        if (detailVm.sNum.value == n) detailVm.sNum.value = 0
        // 2) n이 클릭되면: n만 활성화돼야 해
        else detailVm.sNum.value = n
    }
}