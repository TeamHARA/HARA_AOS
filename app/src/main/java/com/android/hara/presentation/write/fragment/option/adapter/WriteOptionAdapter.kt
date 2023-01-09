package com.android.hara.presentation.write.fragment.option.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemAddOptionBinding
import com.android.hara.databinding.ItemWriteOptionBinding
import com.android.hara.presentation.util.GlobalDiffCallBack
import com.android.hara.presentation.write.fragment.option.model.OptionData

class WriteOptionAdapter() :
    ListAdapter<OptionData, RecyclerView.ViewHolder>(GlobalDiffCallBack()) {

    private lateinit var inflater: LayoutInflater

    class ItemAddOptionViewHolder(val binding: ItemAddOptionBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ItemOptionViewHolder(val binding: ItemWriteOptionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return when (viewType) {
            OPTION -> {
                ItemOptionViewHolder(
                    ItemWriteOptionBinding.inflate(inflater, parent, false)
                )
            }
            else -> {
                ItemAddOptionViewHolder(
                    ItemAddOptionBinding.inflate(inflater, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        when (holder.itemViewType) {
            OPTION -> {
                with(holder as ItemOptionViewHolder) {
                    binding.apply {
                    }
                }
            }
            ADD -> {
                with(holder as ItemAddOptionViewHolder) {
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).veiwType) OPTION
        else ADD
    }

    companion object {
        const val ADD = 1
        const val OPTION = 2
    }
}
