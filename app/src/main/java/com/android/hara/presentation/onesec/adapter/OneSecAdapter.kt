package com.android.hara.presentation.onesec.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.BR
import com.android.hara.databinding.ItemLastWorryBinding
import com.android.hara.presentation.onesec.model.WorryData
import com.android.hara.presentation.util.GlobalDiffCallBack


class OneSecAdapter() :
    ListAdapter<WorryData, RecyclerView.ViewHolder>(GlobalDiffCallBack()) {

    private lateinit var inflater: LayoutInflater

    class ItemViewHolder(val binding: ItemLastWorryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return ItemViewHolder(ItemLastWorryBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as ItemViewHolder) {
            binding.setVariable(BR.worrydata, getItem(position) as WorryData)
        }
    }
}