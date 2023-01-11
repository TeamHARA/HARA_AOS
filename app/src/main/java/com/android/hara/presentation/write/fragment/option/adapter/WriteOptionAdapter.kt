package com.android.hara.presentation.write.fragment.option.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemAddOptionBinding
import com.android.hara.databinding.ItemWriteOptionBinding
import com.android.hara.presentation.util.setOnSingleClickListener
import com.android.hara.presentation.write.fragment.option.model.OptionData


class WriteOptionAdapter(
    private val itemClickListener: (OptionData) -> Unit,
    private val checkEnableListener: (Boolean) -> Unit
) : ListAdapter<OptionData, RecyclerView.ViewHolder>(IngredientDiffCallBack) {

    private lateinit var inflater: LayoutInflater

    init {
        setHasStableIds(true)// 포지션 값 해쉬값으로 저장
    }

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

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        //positoin은 0 부터
        val currentItem = getItem(position)
        when (holder.itemViewType) {
            OPTION -> {
                with((holder as ItemOptionViewHolder).binding) {
                    inputText = titleList[position]
                    etWriteOptionInput.doOnTextChanged { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
                        titleList[position] = charSequence.toString()
                        checkEnableListener(
                            titleList.subList(0, currentList.size - 1)
                                .all { it != "" }) // 모든 에딧텍스트에 입력이 되었는가 검사`
                    }
                    if (position >= 2) { // - 버튼은 3번 선택지 부터 활성화
                        ibOptionDeleteButton.visibility = View.VISIBLE
                    }
                    ibOptionDeleteButton.setOnSingleClickListener {
                        removeItem(position)
                    }
                }
            }
            ADD -> {
                with((holder as ItemAddOptionViewHolder).binding) {
                    if (currentList.size == 5) {
                        // 마지막아이템은 무조건 + 버튼이고
                        //현재 리스트가 5 == 선택지 4개이면 마지막 항목인 +는 보여지면 안된다
                        root.visibility = View.GONE
                    } else {
                        root.visibility = View.VISIBLE
                    }
                    root.setOnSingleClickListener {
                        itemClickListener(currentItem)
                        //아이템 추가할때도 어댑터 갱신하고 다시 enabled 검사
                        checkEnableListener(
                            titleList.subList(0, currentList.size)
                                .all { it != "" })
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).veiwType) OPTION
        else ADD
    }

    private fun removeItem(position: Int) {
        val newList = currentList.toMutableList()
        if (currentList.size == 5) {
            if (position == 2) {
                newList.removeAt(3)
                titleList[2] = titleList[3]
                titleList[3] = ""
            } else {
                newList.removeAt(3)
                titleList[3] = ""
            }
        } else {
            newList.removeAt(2)
            titleList[2] = ""
        }
        submitList(newList)
    }

    companion object {
        const val ADD = 1
        const val OPTION = 2

        private object IngredientDiffCallBack : DiffUtil.ItemCallback<OptionData>() {
            override fun areItemsTheSame(
                oldItem: OptionData, newItem: OptionData
            ): Boolean {
                return false
            }

            override fun areContentsTheSame(
                oldItem: OptionData, newItem: OptionData
            ): Boolean {
                return false
            }
        }

        private val titleList = mutableListOf<String>("", "", "", "")
        fun getAdapterTitleList(): MutableList<String> {
            return titleList
        }

        fun setAdapterTitleList(list: MutableList<String>) {
            titleList.clear()
            titleList.addAll(list)
        }
    }
}
