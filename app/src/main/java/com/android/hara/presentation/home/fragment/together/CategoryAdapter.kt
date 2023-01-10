package com.android.hara.presentation.home.fragment.together

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemCategoryBinding
import java.util.prefs.NodeChangeListener

class CategoryAdapter(
    private val context: Context,
    private val list: ArrayList<SimpleModel>,
    private val changeListener: (Int) -> Unit
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
                binding.btnCategoryButton.isSelected = true
            } else {
                list[absoluteAdapterPosition].isSelected = false
                binding.btnCategoryButton.isSelected = false
            }

            if (onItemClickListener != null) {
                binding.btnCategoryButton.setOnClickListener {
                    onItemClickListener?.onItemClick(item, absoluteAdapterPosition)
                    if (selectedPosition != absoluteAdapterPosition) {
                        binding.btnCategoryButton.isSelected = true
                        notifyItemChanged(selectedPosition)
                        selectedPosition = absoluteAdapterPosition

                        // [수현] 카테고리 item이 클릭되면, 어댑터에 파라미터로 온 함수에, selectedPosition을 넘겨준다
                        changeListener(selectedPosition)
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
}
