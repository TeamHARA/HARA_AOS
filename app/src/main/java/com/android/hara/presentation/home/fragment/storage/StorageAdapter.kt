package com.android.hara.presentation.home.fragment.storage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemStorageBinding
import com.android.hara.presentation.home.fragment.storage.model.StorageData
import com.android.hara.presentation.util.GlobalDiffCallBack

class StorageAdapter(private val itemClickListener: (String) -> Unit)
    : ListAdapter<StorageData, RecyclerView.ViewHolder>(GlobalDiffCallBack()) {

        private lateinit var inflater: LayoutInflater // 뷰 그려줄려고

        class ItemStorageViewHolder(val binding: ItemStorageBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ItemStorageViewHolder(ItemStorageBinding.inflate(inflater,parent,false))
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val currentItem = getItem(position)
            with(holder as ItemStorageViewHolder) {
                //TODO Binding 뭐뭐
            }
        }

}