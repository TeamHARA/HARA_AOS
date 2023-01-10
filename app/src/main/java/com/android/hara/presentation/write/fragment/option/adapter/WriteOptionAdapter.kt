package com.android.hara.presentation.write.fragment.option.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.databinding.ItemAddOptionBinding
import com.android.hara.databinding.ItemWriteOptionBinding
import com.android.hara.presentation.util.setOnSingleClickListener
import com.android.hara.presentation.write.fragment.option.model.OptionData
import timber.log.Timber


class WriteOptionAdapter(
    private val itemClickListener: (OptionData) -> Unit,
    private val checkEnableListener: (Boolean) -> Unit
) : ListAdapter<OptionData, RecyclerView.ViewHolder>(IngredientDiffCallBack) {

    private lateinit var inflater: LayoutInflater
    private val titleList = mutableListOf<String?>("", "", "", "")
    // 각각 EditText의 입력값을 저장


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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        //positoin은 0 부터
        val currentItem = getItem(position)

        Timber.e(position.toString())
        when (holder.itemViewType) {

            OPTION -> {
                with(holder as ItemOptionViewHolder) {
                    binding.inputText = titleList[absoluteAdapterPosition]
                    binding.etWriteOptionInput.addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(
                            charSequence: CharSequence, i: Int, i1: Int, i2: Int
                        ) {
                        }

                        override fun onTextChanged(
                            charSequence: CharSequence, i: Int, i1: Int, i2: Int
                        ) {
                            Timber.e(titleList.toString())
                            checkEnableListener(titleList.all { it != "" }) // 모든 에딧텍스트에 입력이 되었는가 검사
                            titleList[position] = charSequence.toString()
                        }

                        override fun afterTextChanged(editable: Editable) {}
                    })
                    if (position >= 2) { // - 버튼은 3번 선택지 부터 활성화
                        binding.ibOptionDeleteButton.visibility = View.VISIBLE
                    }

                    binding.ibOptionDeleteButton.setOnSingleClickListener {
                        removeItem(position)
                    }
                }
            }
            ADD -> {
                with(holder as ItemAddOptionViewHolder) {
                    if (currentList.size == 5) {
                        // 마지막아이템은 무조건 + 버튼이고
                        //현재 리스트가 5 == 선택지 4개이면 마지막 항목인 +는 보여지면 안된다
                        binding.root.visibility = View.GONE
                    } else {
                        binding.root.visibility = View.VISIBLE
                    }
                    binding.root.setOnSingleClickListener {
                        itemClickListener(currentItem)
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
        newList.removeAt(position)
        submitList(newList)
        //notifyItemRemoved(position)
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
    }
}
