package com.android.hara.presentation.home.fragment.together.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.R
import com.android.hara.data.model.response.AllPostResDto
import com.android.hara.data.model.response.VoteResDto
import com.android.hara.databinding.ItemPostBinding
import com.android.hara.presentation.detail.model.DecideData
import com.android.hara.presentation.home.fragment.together.DetailData
import com.android.hara.presentation.util.GlobalDiffCallBack
import com.android.hara.presentation.util.setOnSingleClickListener
import timber.log.Timber

class PostAdapter(
    private val detailListener: (DetailData) -> Unit,
    private val optSelListener: (postId: Int, optId: Int) -> Unit,
    private val btnSelListener: () -> Unit,
    private val getOptVoteRate: () -> List<VoteResDto.Data.Option>?,
    private val gotoListener: (DecideData) -> Unit
) : ListAdapter<AllPostResDto.Data, RecyclerView.ViewHolder>(GlobalDiffCallBack()) {

    private lateinit var inflater: LayoutInflater // 뷰를 그려준다
    private var clickOpt = mutableMapOf<Int, Int>() // 글 id : 선택한 옵션 id

    class ItemPostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    // view holder 객체를 생성한다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater =
                LayoutInflater.from(parent.context) // parent : adapter가 호출된 activity나 fragment
        }
        return ItemPostViewHolder(ItemPostBinding.inflate(inflater, parent, false))
    }

    // 생성된 view holder에 데이터를 바인딩해준다
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curItem = getItem(position)
        val imgToOpt = mutableListOf<Int>(-1, -1)

        with(holder as ItemPostViewHolder) {
            binding.tvPostCategory.text = curItem.category // 카테고리
            binding.tvPostDate.text = curItem.createdAt // 날짜
            binding.tvPostTitle.text = curItem.title // 글 제목
            binding.tvPostContent.text = curItem.content // 글 본문
            binding.tvPostCommentNum.text = curItem.commentCount.toString() // 댓글 수 카운트
            binding.root.setOnClickListener { // 전체 아이템 누르면 상세화면 이동
                detailListener(
                    DetailData(
                        worryId = curItem.worryId,
                        isVoted = curItem.isVoted,
                        isAuthor = curItem.isAuthor
                    )
                )
            }
            // [최종결정 하러가기] 버튼
            binding.btnPostGoDecide.setOnSingleClickListener {
                // TODO 최종결정 하러 activity 이동
                val isAlone = curItem.commentCount == null
                var including = false // default: image X=
                val optionId = mutableListOf<Int>()
                val optionTitle = mutableListOf<String>()
                val optionPer = mutableListOf<Int?>()

                curItem.option.forEachIndexed { index, option ->
                    if (option.hasImage) including = true
                    optionId.add(option.id)
                    optionTitle.add(option.title)
                    optionPer.add(option.percentage)
                }

                var decideData = DecideData(
                    curItem.worryId,
                    curItem.title,
                    optionId,
                    optionTitle,
                    optionPer,
                    isAlone,
                    including
                )
                Timber.e(decideData.toString())
                // 화면 전환
                gotoListener(decideData)
            }
            // 각 옵션 뷰에, 서버통신으로 받은 데이터(옵션)를 넣어준다
            setOptTitle(binding, curItem)

            // 예) curItem의 옵션 4개 중 첫 번째(option[0])가 이미지를 가진다면
            // imgToOpt[0]에 그 옵션의 id를 저장하는 식
            curItem.option.forEachIndexed { index, opt ->
                if (opt.hasImage) {
                    if (imgToOpt[0] == -1) {
                        imgToOpt[0] = opt.id
                        binding.ivPostImg1.setImageResource(R.drawable.img_card_result)
                        binding.ivPostImg1.visibility = View.VISIBLE
                        binding.itImgSel1 = 0 // 초기값: 아무 옵션도 선택 안 된 상태, 둘다 선명히 보임
                    } else if (imgToOpt[1] == -1) {
                        imgToOpt[1] = opt.id
                        binding.ivPostImg2.setImageResource(R.drawable.img_card_result)
                        binding.ivPostImg2.visibility = View.VISIBLE
                        binding.itImgSel2 = 0 // 초기값: 아무 옵션도 선택 안 된 상태, 둘다 선명히 보임
                    }
                }
            }
            if (imgToOpt[0] == -1) binding.ivPostImg1.visibility = View.GONE
            if (imgToOpt[1] == -1) binding.ivPostImg2.visibility = View.GONE

            // 1) 내가 쓴 글이면: '최종결정 하러가기' 버튼
            if (curItem.isAuthor) {
                binding.itOptClickable = false // 옵션 clickable = false
                binding.itMyPost = true // 옵션에 check 표시 안 보임, '최종결정 하러가기' 버튼이 보임
                binding.itOptSelNum = -1
                // 이런 식으로 값을 안 주면 뷰가 재사용 될 때 itOptSelNum 값이 초기화가 안 돼서 옵션이 선택된 것처럼 나올 수도

                binding.itVoteOptSel = 0 // 초기화.?

                // 내가 쓴 글은, 이미지가 무조건 선명하게 보이게
                // 이미지를 갖는 옵션이 있는지, 어떤 옵션이 몇 번째 이미지 뷰를 갖는지 체크하는 로직은 없음
                binding.itImgSel1 = 1
                binding.itImgSel2 = 1

                bindTurnout(binding, curItem.option)

                // 내가 쓴 글에서, 가장 높은 투표율의 옵션을 파란색으로 보여주는 로직 - 삭제
                /*
                var temp = curItem.option[0].percentage ?: 0
                var maxOptNum = 0 // 옵션1(1~4)에 담긴 %값
                curItem.option.forEachIndexed { index, opt ->
                    if (temp < (opt.percentage ?: 0)) {
                        temp = opt.percentage ?: 0
                        maxOptNum = index
                    }
                }
                binding.itVoteOptSel = maxOptNum + 1
                */


            }

            // 2) 내가 쓴 글이 아니면(남이 쓴 글이면): 투표 버튼
            else {
                binding.itMyPost = false // '투표하기' 버튼이 보임

                // 2-a) 투표를 이미 했으면: '투표 완료!' 버튼, 옵션 스타일
                if (curItem.isVoted) {
                    binding.itOptClickable = false // 옵션 clickable = false
                    binding.itOptSelNum = -1 // 옵션에 check src, 투표 버튼 disable

                    // 유저가 투표한 옵션의 id를 binding.itVoteOptSel에 넘긴다
                    assignVoteOptNum(curItem, binding)

                    // 이미지가 달린 옵션이 선택됐다면 해당 이미지만 활성화해야 한다
                    activateSelOptImage(binding, curItem, binding.itVoteOptSel - 1, imgToOpt)

                    // 투표율을 보여준다
                    bindTurnout(binding, curItem.option)
                }
                // 2-b) 투표를 아직 안 했으면: '투표하기' 버튼 - 선택하면 바꿔야 됨
                else {
                    binding.itOptClickable = true // 옵션 clickable = true
                    binding.itOptSelNum = 0 // 옵션에 check src, 투표 버튼 enable

                    binding.itVoteOptSel = 0 // 아직 투표 안 했으니까 투표한 옵션# = (임의값)0

                    /* 옵션 클릭 시 : 옵션/투표 버튼 스타일이 바뀌는 로직 */
                    // [옵션 1] 버튼
                    binding.layoutPostOpt1.clPostOpt.setOnClickListener {
                        clickOpt.put(curItem.worryId, changeXmlOptSelNum(binding, 1, curItem))

                        Timber.e(
                            "방곰 선택된 애는: " +
                                "글id: " + curItem.worryId.toString() +
                                " 옵션id:" + clickOpt[curItem.worryId].toString()
                        )

                        // [이미지 뷰] 옵션에 해당하는 이미지가 있었다면, 옵션 클릭 시 해당 이미지도 활성화
                        clickImgActivate(binding, binding.itOptSelNum, curItem.option[0], imgToOpt)
                        Timber.e(binding.itOptSelNum.toString())
                    }
                    // [옵션 2] 버튼
                    binding.layoutPostOpt2.clPostOpt.setOnClickListener {
                        clickOpt.put(curItem.worryId, changeXmlOptSelNum(binding, 2, curItem))
                        clickImgActivate(binding, binding.itOptSelNum, curItem.option[1], imgToOpt)
                        Timber.e(
                            "방곰 선택된 애는: " +
                                "글id: " + curItem.worryId.toString() +
                                " 옵션id:" + clickOpt[curItem.worryId].toString()
                        )
                    }
                    // [옵션 3] 버튼
                    binding.layoutPostOpt3.clPostOpt.setOnClickListener {
                        clickOpt.put(curItem.worryId, changeXmlOptSelNum(binding, 3, curItem))
                        clickImgActivate(binding, binding.itOptSelNum, curItem.option[2], imgToOpt)
                        Timber.e(
                            "방곰 선택된 애는: " +
                                "글id: " + curItem.worryId.toString() +
                                " 옵션id:" + clickOpt[curItem.worryId].toString()
                        )
                    }
                    // [옵션 4] 버튼
                    binding.layoutPostOpt4.clPostOpt.setOnClickListener {
                        clickOpt.put(curItem.worryId, changeXmlOptSelNum(binding, 4, curItem))
                        clickImgActivate(binding, binding.itOptSelNum, curItem.option[3], imgToOpt)
                        Timber.e(
                            "방곰 선택된 애는: " +
                                "글id: " + curItem.worryId.toString() +
                                " 옵션id:" + clickOpt[curItem.worryId].toString()
                        )
                    }

                    // [투표하기] 버튼
                    binding.btnPostVote.setOnSingleClickListener {
                        // 전에 클릭했던 게 존재하면, 그 글/옵션에 대해 투표(post 통신)
                        if (clickOpt[curItem.worryId] != -1) {
                            optSelListener(curItem.worryId, clickOpt[curItem.worryId] ?: -100)
                            btnSelListener()

                            binding.itOptClickable = false // 옵션 clickable = false

                            binding.itVoteOptSel = binding.itOptSelNum

                            binding.itOptSelNum = -1 // 옵션에 check src, 투표 버튼 disable

                            // 투표율을 보여준다
                            // getOptVoteRate() -> AllPostResDto.Option 타입으로 바꿔야 됨
                            var voteRes = mutableListOf<AllPostResDto.Data.Option>()
                            getOptVoteRate()?.forEachIndexed { index, opt ->
                                voteRes.add(
                                    AllPostResDto.Data.Option(
                                        opt.hasImage,
                                        opt.id,
                                        opt.image,
                                        opt.percentage,
                                        opt.title,
                                        opt.worryWithId,
                                        "",
                                        ""
                                    )
                                )
                            }

                            bindTurnout(binding, voteRes)

                            // 이미지
                            activateSelOptImage(
                                binding,
                                curItem,
                                binding.itVoteOptSel - 1,
                                imgToOpt
                            )
                        } else {
                            Timber.e("이 글에 대해 옵션을 선택해주시라요!")
                        }
                    }
                }
            }
        }
    }

    private fun changeXmlOptSelNum(
        binding: ItemPostBinding,
        n: Int,
        curItem: AllPostResDto.Data
    ): Int {
        // 옵션에 check src, 투표 버튼 enable/disable
        if (binding.itOptSelNum == n) {
            binding.itOptSelNum = 0
            return -1 // {글id : -1}
        } else {
            binding.itOptSelNum = n
            return curItem.option[n - 1].id // {글id : 선택된 옵션 id}
        }
    }

    // 각 item에, curItem의 option에 있는 title을 바인딩한다
    private fun setOptTitle(binding: ItemPostBinding, curItem: AllPostResDto.Data) {
        // [옵션 1]
        if (curItem.option.size >= 1) {
            binding.layoutPostOpt1.tvPostOptTitle.text = curItem.option.get(0).title // text
            binding.layoutPostOpt1.clPostOptContainer.visibility = View.VISIBLE
        } else {
            binding.layoutPostOpt1.clPostOptContainer.visibility = View.GONE
        }
        // [옵션 2]
        if (curItem.option.size >= 2) {
            binding.layoutPostOpt2.tvPostOptTitle.text = curItem.option.get(1).title // text
            binding.layoutPostOpt2.clPostOptContainer.visibility = View.VISIBLE
        } else {
            binding.layoutPostOpt2.clPostOptContainer.visibility = View.GONE
        }
        // [옵션 3]
        if (curItem.option.size >= 3) {
            binding.layoutPostOpt3.tvPostOptTitle.text = curItem.option.get(2).title // text
            binding.layoutPostOpt3.clPostOptContainer.visibility = View.VISIBLE
        } else {
            binding.layoutPostOpt3.clPostOptContainer.visibility = View.GONE
        }
        // [옵션 4]
        if (curItem.option.size >= 4) {
            binding.layoutPostOpt4.tvPostOptTitle.text = curItem.option.get(3).title // text
            binding.layoutPostOpt4.clPostOptContainer.visibility = View.VISIBLE
        } else {
            binding.layoutPostOpt4.clPostOptContainer.visibility = View.GONE
        }
    }

    private fun assignVoteOptNum(
        curItem: AllPostResDto.Data,
        binding: ItemPostBinding
    ) {
        for (i in 0..curItem.option.size - 1) {
            if (curItem.loginUserVoteId == curItem.option[i].id) {
                binding.itVoteOptSel = i + 1
                break
            } else binding.itVoteOptSel = 1 // TODO 임의값
        }

        // TODO? 여기서 젤 높은 숫자 보여줘야 되나?
    }

    private fun activateSelOptImage(
        binding: ItemPostBinding,
        curItem: AllPostResDto.Data,
        optSelNum: Int,
        imgToOpt: List<Int>
    ) {
        if (curItem.option[optSelNum].hasImage) { // 투표한 옵션이 연결된 이미지가 있음
            if (curItem.option[optSelNum].id == imgToOpt[0]) { // 투표한 옵션이 img1과 연결됨
                binding.itImgSel1 = 1
                binding.itImgSel2 = 0
            } else if (curItem.option[optSelNum].id == imgToOpt[1]) { // 투표한 옵션이 img2와 연결됨
                binding.itImgSel1 = 0
                binding.itImgSel2 = 1
            }
        } else { // 투표한 옵션이 이미지와 연결되지 않음
            binding.itImgSel1 = 0
            binding.itImgSel2 = 0
        }
    }

    private fun clickImgActivate(
        binding: ItemPostBinding,
        itOptSelNum: Int,
        curOpt: AllPostResDto.Data.Option,
        imgToOpt: List<Int>
    ) {
        if (curOpt.hasImage) { // 해당 옵션이 이미지를 가진다
            if (itOptSelNum == 0) { // 아무 옵션도 선택 안 된 상태
                binding.itImgSel1 = 0
                binding.itImgSel2 = 0
            } else { // 어떤 옵션이 선택된 상태
                if (curOpt.id == imgToOpt[0]) { // 그 옵션이 img1에 해당되면: img1만 활성화
                    binding.itImgSel1 = 1
                    binding.itImgSel2 = 0
                } else if (curOpt.id == imgToOpt[1]) { // 그 옵션이 img2에 해당되면: img2만 활성화
                    binding.itImgSel1 = 0
                    binding.itImgSel2 = 1
                }
            }
        } else { // 해당 옵션이 이미지를 갖지 않는다
            binding.itImgSel1 = 0
            binding.itImgSel2 = 0
        }
    }

    // 투표율을 보여준다
    private fun bindTurnout(
        binding: ItemPostBinding,
        curOptList: List<AllPostResDto.Data.Option>
    ) {
        // [옵션 1]
        if (curOptList.size >= 1) {
            binding.layoutPostOpt1.tvPostOptTurnout.text =
                (curOptList[0].percentage?.toString() ?: 0f.toString()) + "%"
            binding.layoutPostOpt1.pbTurnout.progress = curOptList[0].percentage?.toFloat() ?: 0f
        }

        // [옵션 2]
        if (curOptList.size >= 2) {
            binding.layoutPostOpt2.tvPostOptTurnout.text =
                (curOptList[1].percentage?.toString() ?: 0f.toString()) + "%"
            binding.layoutPostOpt2.pbTurnout.progress = curOptList[1].percentage?.toFloat() ?: 0f
        }

        // [옵션 3]
        if (curOptList.size >= 3) {
            binding.layoutPostOpt3.tvPostOptTurnout.text =
                (curOptList[2].percentage?.toString() ?: 0f.toString()) + "%"
            binding.layoutPostOpt3.pbTurnout.progress = curOptList[2].percentage?.toFloat() ?: 0f
        }

        // [옵션 4]
        if (curOptList.size >= 4) {
            binding.layoutPostOpt4.tvPostOptTurnout.text =
                (curOptList[3].percentage?.toString() ?: 0f.toString()) + "%"
            binding.layoutPostOpt4.pbTurnout.progress = curOptList[3].percentage?.toFloat() ?: 0f
        }
    }
} // class PostAdapter
