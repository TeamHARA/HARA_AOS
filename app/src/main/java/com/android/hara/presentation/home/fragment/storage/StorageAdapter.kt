package com.android.hara.presentation.home.fragment.storage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.data.model.response.WorryListResDto
import com.android.hara.databinding.ItemStorageBinding
import com.android.hara.presentation.home.fragment.together.DetailData
import com.android.hara.presentation.util.GlobalDiffCallBack

class StorageAdapter(private val itemClickListener: (DetailData) -> Unit) :
    ListAdapter<WorryListResDto.Data, RecyclerView.ViewHolder>(GlobalDiffCallBack()) {

    private lateinit var inflater: LayoutInflater // 뷰 그려줄려고

    class ItemStorageViewHolder(val binding: ItemStorageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
        return ItemStorageViewHolder(ItemStorageBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curItem = getItem(position)
        var ing = curItem.finalOption == null
        with(holder as ItemStorageViewHolder) {
            binding.worry = curItem
            binding.xmlIng = ing
            binding.root.setOnClickListener {
                itemClickListener(
                    DetailData(
                        worryId = curItem.id,
                        isVoted = false,
                        isAuthor = false
                    )
                )
            }
            if (ing) binding.tvStorageFlag.text = "고민중"
            else binding.tvStorageFlag.text = "고민완료"
        }
    }
}
