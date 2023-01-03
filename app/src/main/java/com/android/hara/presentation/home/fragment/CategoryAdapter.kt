package com.android.hara.presentation.home.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemCategoryBinding

// private val itemClickListener: (String) -> Unit : ?
class CategoryAdapter(context: Context, private val itemClickListener: (String) -> Unit) : ListAdapter<String, RecyclerView.ViewHolder>(TestDiffCallback()) {
    // notifyDatasetChange대신 submitlist 리스트를 넣는다

    private val inflater by lazy { LayoutInflater.from(context) }

    class TestDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ItemCategoryBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        with(holder as ItemViewHolder) {
            binding.btnCategoryButton.text = currentItem
            binding.btnCategoryButton.setOnClickListener {
                itemClickListener(currentItem)
                binding.btnCategoryButton.isSelected = true
            }
        }
    }
}
