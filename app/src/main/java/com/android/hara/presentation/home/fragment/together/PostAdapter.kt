package com.android.hara.presentation.home.fragment.together

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemPostBinding
import com.android.hara.presentation.home.fragment.together.model.TogetherPostData
import com.android.hara.presentation.util.GlobalDiffCallBack

class PostAdapter(private val itemClickListener: (String) -> Unit)
    : ListAdapter<TogetherPostData, RecyclerView.ViewHolder>(GlobalDiffCallBack()){

        private lateinit var inflater: LayoutInflater // 뷰를 그려준다

        class ItemPostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            if(!::inflater.isInitialized) {
                inflater = LayoutInflater.from(parent.context)
            }
            return ItemPostViewHolder(ItemPostBinding.inflate(inflater, parent, false))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val curItem = getItem(position)
            with(holder as ItemPostViewHolder) {
                binding.tvPostCategory.text = curItem.category // 카테고리
                binding.tvPostDate.text = curItem.date // 날짜
                binding.tvPostTitle.text = curItem.title // 글 제목
                binding.tvPostContent.text = curItem.content // 글 본문
                binding.tvPostCommentNum.text = curItem.commentNum.toString() // 댓글 수 카운트

                // TODO: 이미지가 있다면, 몇 개 있는지(최대 2개?)를 알고 그걸 보여줘야 함

                // TODO: 옵션이 몇 개인지 세고 그 개수만큼 보여줘야 함
                binding.layoutPostOpt1.tvPostOptTitle.text = curItem.opt1 // 옵션1 text
                binding.layoutPostOpt2.tvPostOptTitle.text = curItem.opt2 // 옵션2 text
                binding.layoutPostOpt3.tvPostOptTitle.text = curItem.opt3 // 옵션3 text
                binding.layoutPostOpt4.tvPostOptTitle.text = curItem.opt4 // 옵션4 text
            }
        }
} // class PostAdapter