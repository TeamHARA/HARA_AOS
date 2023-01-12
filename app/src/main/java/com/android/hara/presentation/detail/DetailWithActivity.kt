package com.android.hara.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityDetailWithBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.custom.DecisionDialog
import com.android.hara.presentation.custom.model.DialogData
import com.android.hara.presentation.detail.adapter.CommentAdapter
import com.android.hara.presentation.detail.viewmodel.DetailViewWithModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailWithActivity :
    BindingActivity<ActivityDetailWithBinding>(R.layout.activity_detail_with) {
    //TODO [고민글 상세보기] 부분입니다. 추후 네이밍 변경 예정

    private val detailVm: DetailViewWithModel by viewModels()
    private lateinit var commentAdapter: CommentAdapter
    private var imageFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val worryId = intent.getIntExtra("worryId", 0)
//        detailVm.getDetailWith(worryId)
        detailVm.getDetailWith(12)

        val bindingList = listOf(
            binding.layoutOption1,
            binding.layoutOption2,
            binding.layoutOption3,
            binding.layoutOption4
        )

        detailVm.success.observe(this) {
            if (it) {
                binding.detailVm = detailVm
                detailVm.detailDto.value!!.data.options.forEachIndexed { index, option ->
                    bindingList[index].root.visibility = View.VISIBLE // 선택지 갯수 만큼 visibilty 조절
                    //if (option.hasImage) binding.flowImage.vi // 하나라도 이미지 있다면 Flag발동
                }
                if (detailVm.detailDto.value!!.data.commentCount > 0) {
                    commentAdapter = CommentAdapter()
                    binding.rcvComment.adapter = commentAdapter
                    binding.count = detailVm.detailDto.value!!.data.commentCount
                    commentAdapter.submitList(detailVm.detailDto.value!!.data.comments)
                } else {
                    //TODO 엠티뷰
                }
            }
        }

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