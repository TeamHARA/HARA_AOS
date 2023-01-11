package com.android.hara.presentation.home.fragment.together

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val context: Context,
    private val list: ArrayList<SimpleModel>
) : RecyclerView.Adapter<CategoryAdapter.SelectSingleItemViewHolder>() {

    private lateinit var binding: ItemCategoryBinding
    private var onItemClickListener: OnItemClickListener? = null

    private var selectedPosition = 0

    interface OnItemClickListener {
        fun onItemClick(item: SimpleModel, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class SelectSingleItemViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimpleModel) {
            binding.model = item

            if (selectedPosition == absoluteAdapterPosition) {
                list[absoluteAdapterPosition].isSelected = true
//                binding.setChecked()
                binding.btnCategoryButton.isSelected = true
            } else {
                list[absoluteAdapterPosition].isSelected = false
//                binding.setUnchecked()
                binding.btnCategoryButton.isSelected = false
            }

            if (onItemClickListener != null) {
                binding.btnCategoryButton.setOnClickListener {
                    onItemClickListener?.onItemClick(item, absoluteAdapterPosition)
                    if (selectedPosition != absoluteAdapterPosition) {
//                        binding.setChecked()
                        binding.btnCategoryButton.isSelected = true
                        notifyItemChanged(selectedPosition)
                        selectedPosition = absoluteAdapterPosition
                        TogetherFragment.setScroll()
                    } else {
                        TogetherFragment.setScroll()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSingleItemViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return SelectSingleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectSingleItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

//    private fun ItemCategoryBinding.setChecked() = binding.btnCategoryButton.selected(true)
//    private fun ItemCategoryBinding.setUnchecked() = binding.btnCategoryButton.selected(false)
}
