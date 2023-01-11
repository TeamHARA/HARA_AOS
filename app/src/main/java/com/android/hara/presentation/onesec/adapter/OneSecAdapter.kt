package com.android.hara.presentation.onesec.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.data.model.response.RandomListResDto
import com.android.hara.databinding.ItemLastWorryBinding
import com.android.hara.presentation.util.GlobalDiffCallBack

class OneSecAdapter :
    ListAdapter<RandomListResDto.Data, OneSecAdapter.ItemViewHolder>(GlobalDiffCallBack()) {

    private lateinit var inflater: LayoutInflater

    class ItemViewHolder(val binding: ItemLastWorryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return ItemViewHolder(ItemLastWorryBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curItem = getItem(position)
        with(holder as ItemViewHolder) {
            binding.worrydata = curItem
        }
    }
}
