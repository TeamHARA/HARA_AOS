package com.android.hara.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.data.model.response.DetailWithResDto
import com.android.hara.databinding.ItemCommentBinding
import com.android.hara.presentation.util.GlobalDiffCallBack

class CommentAdapter :
    ListAdapter<DetailWithResDto.Data.Comment, CommentAdapter.ItemViewHolder>(GlobalDiffCallBack()) {

    private lateinit var inflater: LayoutInflater

    class ItemViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return ItemViewHolder(ItemCommentBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curItem = getItem(position)
        with(holder) {
            binding.comment = curItem
        }
    }

}