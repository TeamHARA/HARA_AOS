package com.android.hara.presentation.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityDetailWithBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.detail.adapter.CommentAdapter
import com.android.hara.presentation.detail.viewmodel.DetailWithViewModel
import com.android.hara.presentation.home.fragment.together.DetailData
import com.android.hara.presentation.home.viewmodel.HomeViewModel
import com.android.hara.presentation.util.HARAobjcet.categoryList
import com.android.hara.presentation.util.HARAobjcet.nicknameList
import com.android.hara.presentation.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailWithActivity :
    BindingActivity<ActivityDetailWithBinding>(R.layout.activity_detail_with) {
    //TODO [고민글 상세보기] 부분입니다. 추후 네이밍 변경 예정

    private val homeVm by viewModels<HomeViewModel>() // vote (post 통신)
    private val detailVm: DetailWithViewModel by viewModels()
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val worryData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("detailData", DetailData::class.java)
        } else {
            intent.getParcelableExtra("detailData")
        }

        detailVm.getDetailWith(worryData!!.worryId)
        //detailVm.getDetailWith(12)

        val bindingList = listOf(
            binding.layoutOption1,
            binding.layoutOption2,
            binding.layoutOption3,
            binding.layoutOption4
        )

        detailVm.success.observe(this) {
            if (it) {
                binding.detailVm = detailVm
                if (detailVm.detailDto.value!!.data.isAuthor) binding.nickname = nicknameList[0]
                else binding.nickname = nicknameList[(0..8).random()]
                binding.category = categoryList[detailVm.detailDto.value?.data?.category ?: 1]

                if (detailVm.detailDto.value?.data?.finalOption != null) binding.appbarDetail.title =
                    this.getString(R.string.storage_filter_com)
                detailVm.detailDto.value!!.data.options.forEachIndexed { index, option ->
                    // 선택지 갯수 만큼 visibilty 조절
                    bindingList[index].root.visibility = View.VISIBLE

                    // 하나라도 이미지 있다면 flag 발동
                    if (option.hasImage)
                        binding.flowImage.visibility = View.VISIBLE

                    // 각 옵션 뷰에 데이터를 넣어준다
                    with(bindingList[index]) {
                        // 제목
                        title = option.title

                        // 장점
                        if (option.advantage == "") {
                            tvOptProTitle.visibility = View.GONE
                            tvOptProContent.visibility = View.GONE
                        } else {
                            advantage = option.advantage
                            tvOptProTitle.visibility = View.VISIBLE
                            tvOptProContent.visibility = View.VISIBLE
                        }

                        // 단점
                        if (option.disadvantage == "") {
                            tvOptConTitle.visibility = View.GONE
                            tvOptConContent.visibility = View.GONE
                        } else {
                            disadvantage = option.disadvantage
                            tvOptConTitle.visibility = View.VISIBLE
                            tvOptConContent.visibility = View.VISIBLE
                        }

                        // 투표율
                        if (option.percentage == null)
                            tvOptPercent.visibility = View.GONE
                        else percentage = option.percentage
                    }

                    /* [함께고민] */

                    Timber.e(
                        "진입 ..." + binding.itOptSelNum
                                + " " + binding.itVoteOptSelNum
                                + " " + binding.itMyPost
                                + " " + detailVm.detailDto.value!!.data.isAuthor
                    )

                    // 1. [내 글]
                    if (detailVm.detailDto.value!!.data.isAuthor) {
                        var maxVal = 0
                        var maxIndex = -1
                        detailVm.detailDto.value!!.data.options.forEachIndexed { i, opt ->
                            if (maxVal <= opt.percentage ?: 0) {
                                maxVal = opt.percentage ?: 0
                                maxIndex = i
                            }
                        }

                        binding.itMyPost = true
                        binding.itOptSelNum = 0 // 옵션들이 남의글에 대한 투표완료 화면처럼 보여야 됨
                        binding.itVoteOptSelNum = 0 // 투표한 적 없음

                        Timber.e(
                            "나의 글 ..." + binding.itOptSelNum
                                    + " " + binding.itVoteOptSelNum
                                    + " " + binding.itMyPost
                        )
                    }
                    // 2. [남의 글]
                    else {
                        binding.itMyPost = false
                        // 2-a. [투표 완료]
                        if (detailVm.detailDto.value!!.data.isVoted) {
                            binding.itOptSelNum = -1
                            // TODO 서버에서 어떤 옵션에 투표했는지에 대한 정보가 와야 됨
                            for (i in 0..(detailVm.detailDto.value!!.data.options.size - 1)) {
                                if (detailVm.detailDto.value!!.data.selectedOptionId
                                    == detailVm.detailDto.value!!.data.options[i].id
                                ) {
                                    binding.itVoteOptSelNum = i + 1
                                } else break
                            }
                            // binding.itVoteOptSelNum = 1 // TODO 일단 무조건 옵션1에 투표했다고 해보자
                        }
                        // 2-b. [투표 미완]
                        else {
                            // 2-b-ㄱ. 옵션 아무 것도 선택 X
                            // 2-b-ㄴ. 옵션 뭔가 선택

                            binding.itOptSelNum = 0 // 투표할 옵션 선택 중

                            // 옵션 1에 대한 onClickListener
                            bindingList[0].clOptBox.setOnClickListener {
                                changeItOptSelNum(binding, 1)
                                Timber.e(
                                    "please1..." + binding.itOptSelNum
                                            + " " + binding.itVoteOptSelNum
                                )
                                homeVm.changeSelPostAndOptId(
                                    detailVm.detailDto.value!!.data.options[0].worryWithId,
                                    detailVm.detailDto.value!!.data.options[0].id
                                )
                            }
                            // 옵션 2에 대한 onClickListener
                            bindingList[1].clOptBox.setOnClickListener {
                                changeItOptSelNum(binding, 2)
                                Timber.e(
                                    "please2..." + binding.itOptSelNum
                                            + " " + binding.itVoteOptSelNum
                                )
                                homeVm.changeSelPostAndOptId(
                                    detailVm.detailDto.value!!.data.options[1].worryWithId,
                                    detailVm.detailDto.value!!.data.options[1].id
                                )
                            }
                            // 옵션 3에 대한 onClickListener
                            bindingList[2].clOptBox.setOnClickListener {
                                changeItOptSelNum(binding, 3)
                                Timber.e(
                                    "please3..." + binding.itOptSelNum
                                            + " " + binding.itVoteOptSelNum
                                )
                                homeVm.changeSelPostAndOptId(
                                    detailVm.detailDto.value!!.data.options[2].worryWithId,
                                    detailVm.detailDto.value!!.data.options[2].id
                                )
                            }
                            // 옵션 4에 대한 onClickListener
                            bindingList[3].clOptBox.setOnClickListener {
                                changeItOptSelNum(binding, 4)
                                Timber.e(
                                    "please4..." + binding.itOptSelNum
                                            + " " + binding.itVoteOptSelNum
                                )
                                homeVm.changeSelPostAndOptId(
                                    detailVm.detailDto.value!!.data.options[3].worryWithId,
                                    detailVm.detailDto.value!!.data.options[3].id
                                )
                            }

                            // 투표하기 버튼이 눌리면
                            binding.btnDetailVote.setOnSingleClickListener {
                                // homeVm 안의 btnVal 값을 바꿔줌 -> observe에서 감지, 서버 통신
                                homeVm.changeBtnVal()
                                binding.itOptSelNum = -1
                            }
                        }
                    }
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
        } // get 통신

        binding.appbarDetail.setNavigationOnClickListener {
            finish()
        }

        homeVm.btnSel.observe(this) {
            Timber.e("투표 POST " + homeVm.getPostId() + homeVm.getOptId())
            homeVm.homeVmPostVote(homeVm.getPostId(), homeVm.getOptId())
        }

        // n번째 옵션 클릭 시 옵션과 버튼 스타일 변하는 로직
        /*
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
        */
    }

    // n번째 옵션이 선택되면 DetailViewModel 안의 sNum의 value가 n으로 바뀐다
    private fun changeVmSnum(n: Int) { // n이 선택된 상태
        // 1) n이 클릭되면: n만 비활성화돼야 해
        if (detailVm.sNum.value == n) detailVm.sNum.value = 0
        // 2) n이 클릭되면: n만 활성화돼야 해
        else detailVm.sNum.value = n
    }

    private fun changeItOptSelNum(binding: ActivityDetailWithBinding, n: Int) {
        // 1) n이 클릭되면: n만 비활성화돼야 해
        if (binding.itOptSelNum == n) binding.itOptSelNum = 0
        // 2) n이 클릭되면: n만 활성화돼야 해
        else binding.itOptSelNum = n
    }
}