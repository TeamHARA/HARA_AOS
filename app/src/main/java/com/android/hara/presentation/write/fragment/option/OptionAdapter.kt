package com.android.hara.presentation.write.fragment.option

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemWriteOptionBinding
import com.android.hara.presentation.util.GlobalDiffCallBack

class OptionAdapter(context: Context, private val itemClickListener: (Option) -> Unit) :
    ListAdapter<Option, RecyclerView.ViewHolder>(GlobalDiffCallBack()) {
    private val inflater by lazy { LayoutInflater.from(context) }

    class ItemViewHolder(val binding: ItemWriteOptionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ItemWriteOptionBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        with(holder as ItemViewHolder) {
            binding.ibOptionDeleteButton.setOnClickListener {
                itemClickListener(currentItem)
            }
        }
    }
}
