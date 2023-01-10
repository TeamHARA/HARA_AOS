package com.android.hara.presentation.home.fragment.together

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.data.model.response.AllPostResDto
import com.android.hara.databinding.ItemPostBinding
import com.android.hara.presentation.home.fragment.together.model.TogetherPostData
import com.android.hara.presentation.util.GlobalDiffCallBack

class PostAdapter(
    private val optSelListener: (postId: Int, optId: Int) -> Unit,
    private val btnSelListener: () -> Unit
) : ListAdapter<AllPostResDto.Data, RecyclerView.ViewHolder>(GlobalDiffCallBack()) {

    private lateinit var inflater: LayoutInflater // 뷰를 그려준다

    class ItemPostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    // view holder 객체를 생성한다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context) // parent : adapter가 호출된 activity나 fragment
        }
        return ItemPostViewHolder(ItemPostBinding.inflate(inflater, parent, false))
    }

    // 생성된 view holder에 데이터를 바인딩해준다
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curItem = getItem(position)
        with(holder as ItemPostViewHolder) {
            binding.tvPostCategory.text = curItem.category // 카테고리
            binding.tvPostDate.text = curItem.createdAt // 날짜
            binding.tvPostTitle.text = curItem.title // 글 제목
            binding.tvPostContent.text = curItem.content // 글 본문
            binding.tvPostCommentNum.text = curItem.commentCount.toString() // 댓글 수 카운트

            // TODO: 이미지가 있다면, 몇 개 있는지(최대 2개?)를 알고 그걸 보여줘야 함

            // TODO: 옵션이 몇 개인지 세고 그 개수만큼 보여줘야 함

            // [옵션 1]
            binding.layoutPostOpt1.tvPostOptTitle.text = curItem.option.get(0).title // text

            // [옵션 2]
            binding.layoutPostOpt2.tvPostOptTitle.text = curItem.option.get(1).title // text

            // [옵션 3]
            if (curItem.option.size >= 3) {
                binding.layoutPostOpt3.tvPostOptTitle.text = curItem.option.get(2).title // text
            }
            else {
                binding.layoutPostOpt3.clPostOpt.visibility = View.GONE
            }

            // [옵션 4]
            if (curItem.option.size >= 4) {
                binding.layoutPostOpt4.tvPostOptTitle.text = curItem.option.get(3).title // text
            }
            else {
                binding.layoutPostOpt4.clPostOpt.visibility = View.GONE
            }

            /* 옵션 클릭 시: 옵션/투표 버튼 스타일이 바뀌는 로직 */
            binding.layoutPostOpt1.clPostOpt.setOnClickListener {
                changeXmlOptSelNum(binding, 1)

                // [수현] 옵션이 클릭되면, 어댑터에 파라미터로 온 함수에, 그 글의 id와 그 옵션의 id를 넘겨준다
                optSelListener(curItem.worryId, curItem.option[0].id)
            }
            binding.layoutPostOpt2.clPostOpt.setOnClickListener {
                changeXmlOptSelNum(binding, 2)
                optSelListener(curItem.worryId, curItem.option[1].id)
            }
            binding.layoutPostOpt3.clPostOpt.setOnClickListener {
                changeXmlOptSelNum(binding, 3)
                optSelListener(curItem.worryId, curItem.option[2].id)
            }
            binding.layoutPostOpt4.clPostOpt.setOnClickListener {
                changeXmlOptSelNum(binding, 4)
                optSelListener(curItem.worryId, curItem.option[3].id)
            }

            binding.btnPostVote.setOnClickListener {
                btnSelListener()
            }
        }
    }

    fun changeXmlOptSelNum(binding: ItemPostBinding, n: Int) {
        if (binding.optSelNum == n) binding.optSelNum = 0
        else binding.optSelNum = n
    }

} // class PostAdapter