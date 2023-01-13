package com.android.hara.presentation.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.android.hara.R
import com.android.hara.databinding.ActivityDetailWithBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.detail.adapter.CommentAdapter
import com.android.hara.presentation.detail.decision.FinalDecideActivity
import com.android.hara.presentation.detail.model.DecideData
import com.android.hara.presentation.detail.viewmodel.DetailWithViewModel
import com.android.hara.presentation.home.fragment.together.DetailData
import com.android.hara.presentation.home.viewmodel.HomeViewModel
import com.android.hara.presentation.util.HARAobjcet
import com.android.hara.presentation.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailWithActivity :
    BindingActivity<ActivityDetailWithBinding>(R.layout.activity_detail_with) {
    // TODO [고민글 상세보기] 부분입니다. 추후 네이밍 변경 예정

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
        // detailVm.getDetailWith(12)

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

                if (detailVm.detailDto.value?.data?.finalOption != null) binding.appbarDetail.title =
                    this.getString(R.string.storage_filter_com)
                detailVm.detailDto.value!!.data.options.forEachIndexed { index, option ->
                    // 선택지 갯수 만큼 visibilty 조절
                    bindingList[index].root.visibility = View.VISIBLE

                    // 하나라도 이미지 있다면 flag 발동
                    if (option.hasImage) {
                        binding.flowImage.visibility = View.VISIBLE
                    }

                    // 각 옵션 뷰에 데이터를 넣어준다
                    with(bindingList[index]) {
                        // 제목
                        title = option.title

                        // 장점
                        if (option.advantage == null || option.advantage == "") {
                            tvOptProTitle.visibility = View.GONE
                            tvOptProContent.visibility = View.GONE
                        } else {
                            advantage = option.advantage
                            tvOptProTitle.visibility = View.VISIBLE
                            tvOptProContent.visibility = View.VISIBLE
                        }

                        // 단점
                        if (option.disadvantage == null || option.disadvantage == "") {
                            tvOptConTitle.visibility = View.GONE
                            tvOptConContent.visibility = View.GONE
                        } else {
                            disadvantage = option.disadvantage
                            tvOptConTitle.visibility = View.VISIBLE
                            tvOptConContent.visibility = View.VISIBLE
                        }

                        // 투표율
                        if (option.percentage == null) {
                            //tvOptPercent.visibility = View.GONE
                            percentage = "0" + "%"
                        } else percentage = option.percentage.toString() + "%"
                    }

                    /* [함께고민] */

                    Timber.e(
                        "진입 ..." + binding.itOptSelNum +
                            " " + binding.itVoteOptSelNum +
                            " " + binding.itMyPost +
                            " " + detailVm.detailDto.value!!.data.isAuthor
                    )

                    // 1. [내 글]
                    if (detailVm.detailDto.value!!.data.isAuthor!!) {
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
                            "나의 글 ..." + binding.itOptSelNum +
                                " " + binding.itVoteOptSelNum +
                                " " + binding.itMyPost
                        )

                        binding.btnDetailVote.setOnSingleClickListener {
                            val res = detailVm.detailDto.value!!.data
                            var including = false // default: image X=
                            val optionId = mutableListOf<Int>()
                            val optionTitle = mutableListOf<String>()
                            val optionPer = mutableListOf<Int?>()

                            res.options.forEachIndexed { index, option ->
                                if (option.hasImage) including = true
                                optionId.add(option.id)
                                optionTitle.add(option.title)
                                optionPer.add(option.percentage)
                            }
                            val decideData = DecideData(
                                1,
                                res.worryTitle,
                                optionId,
                                optionTitle,
                                optionPer,
                                false,
                                including
                            )

                            startActivity(
                                Intent(
                                    this,
                                    FinalDecideActivity::class.java
                                ).putExtra("decideData", decideData)
                            )
                        }
                    }
                    // 2. [남의 글]
                    else {
                        binding.itMyPost = false
                        // 2-a. [투표 완료]
                        if (detailVm.detailDto.value!!.data.isVoted) {
                            binding.itOptSelNum = -1

                            // 몇 번째 옵션에 투표했는지
                            for (i in 0..(detailVm.detailDto.value!!.data.options.size - 1)) {
                                if (detailVm.detailDto.value!!.data.selectedOptionId
                                    == detailVm.detailDto.value!!.data.options[i].id
                                ) {
                                    binding.itVoteOptSelNum = i + 1
                                    break
                                }
                            }
                            Timber.e("여기다 투표했던 것이여 " + binding.itVoteOptSelNum)

                            showProgressDetailRes(bindingList, detailVm.detailDto.value?.data?.options!!)
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
                                    "please1..." + binding.itOptSelNum +
                                        " " + binding.itVoteOptSelNum
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
                                    "please2..." + binding.itOptSelNum +
                                        " " + binding.itVoteOptSelNum
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
                                    "please3..." + binding.itOptSelNum +
                                        " " + binding.itVoteOptSelNum
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
                                    "please4..." + binding.itOptSelNum +
                                        " " + binding.itVoteOptSelNum
                                )
                                homeVm.changeSelPostAndOptId(
                                    detailVm.detailDto.value!!.data.options[3].worryWithId,
                                    detailVm.detailDto.value!!.data.options[3].id
                                )
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
                    // TODO 엠티뷰
                }
            }

            if (worryData.worryId == 9) { // r
                binding.flowImage.visibility = View.VISIBLE
                bindingList[0].advantage = "꼬질꼬질 귀여운 느낌이라 귀여웡"
                bindingList[0].disadvantage = "브랜딩이랑 조금 다른 느낌이기는 해"
                bindingList[1].advantage = "전체적인 우리 앱의 UI/UX랑 무드가 잘 맞긴해"
                bindingList[1].disadvantage = "귀여운 느낌보다는 딱딱한 것 같기는 해 "
            }
        } // get 통신

        binding.appbarDetail.setNavigationOnClickListener {
            finish()
        }

        // [버튼 누르면] 최종결정 하러가기? 투표하기?
        homeVm.btnSel.observe(this) {
            if (binding.itMyPost) { // [나의 글] 최종결정 하러가기

            }
            else { // [남의 글] 투표하기
                binding.itOptSelNum = -1
                Timber.e("투표 POST " + homeVm.getPostId() + homeVm.getOptId())

                // 1) (detailVm - get해온 옵션 중) 투표한 옵션이 몇 번째인지
                var i: Int = 0
                Timber.e(detailVm.detailDto.value?.data?.options?.size!!.toString())
                while (i < detailVm.detailDto.value?.data?.options?.size!!) {
                    if (detailVm.detailDto.value?.data?.options!![i].id == homeVm.getOptId()) {
                        break // i+1번째 옵션에 투표한 것
                    }
                    i++
                }
                binding.itVoteOptSelNum = i + 1

                // 2) (homeVm - post) 투표하기
                homeVm.homeVmPostVote(homeVm.getPostId(), homeVm.getOptId())

                // 3) (homeVm - post의 response로 받은 걸로) progress 갱신
                var index: Int = 0
                while (index < homeVm.voteResult.value?.data?.size!!) {
                    if (homeVm.voteResult.value?.data!![index].worryId == homeVm.getOptId()) break
                    index++ // data[index]의 option[]에 따라 progress 갱신해야 한다...(dto 구조 참고)
                }
                showProgressVoteRes(bindingList, homeVm.voteResult.value?.data!![index].option)
            }
        }
    }

    private fun changeItOptSelNum(binding: ActivityDetailWithBinding, n: Int) {
        // 1) n이 클릭되면: n만 비활성화돼야 해
        if (binding.itOptSelNum == n) binding.itOptSelNum = 0
        // 2) n이 클릭되면: n만 활성화돼야 해
        else binding.itOptSelNum = n
    }

    // 각 옵션에 대해: progress 값을 보여준다(갱신한다)
    private fun showProgressVoteRes(
        bindList: List<LayoutDetailOptionBinding>,
        optList: List<VoteResDto.Data.Option>)
    {
        bindList.forEachIndexed { i, opt ->
            opt.tvOptPercent.text = optList[i].percentage.toString()
            opt.pbDetailTurnout.progress = optList[i].percentage?.toFloat()!!
        }
    }

    private fun showProgressDetailRes(
        bindList: List<LayoutDetailOptionBinding>,
        optList: List<DetailWithResDto.Data.Option>)
    {
        optList.forEachIndexed { i, opt ->
            bindList[i].tvOptPercent.text = opt.percentage.toString()
            bindList[i].pbDetailTurnout.progress = opt.percentage?.toFloat()!!
        }
    }
}
