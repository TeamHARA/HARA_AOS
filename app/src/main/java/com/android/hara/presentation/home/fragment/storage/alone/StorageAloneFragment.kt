package com.android.hara.presentation.home.fragment.storage.alone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.data.model.response.WorryListResDto
import com.android.hara.databinding.FragmentStorageSelfBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.detail.DetailAloneActivity
import com.android.hara.presentation.home.fragment.storage.StorageAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class StorageAloneFragment :
    BindingFragment<FragmentStorageSelfBinding>(R.layout.fragment_storage_self) {

    private val storageAloneViewModel: StorageAloneViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storageAdapter = StorageAdapter() {
            val intent = Intent(requireContext(), DetailAloneActivity::class.java)
            intent.putExtra("worryId", it)
            startActivity(intent)
        }
        binding.rvPosts.adapter = storageAdapter
        storageAloneViewModel.getAloneList()

        addObserve(storageAdapter)
        onClickToggleBtn(storageAdapter)
    }

    private fun addObserve(storageAdapter: StorageAdapter) {
        storageAloneViewModel.isSolved.observe(viewLifecycleOwner) { isSolved ->
            // TODO 현재는 리스트정렬을 바꿔버리는 식으로 처리 단점은 실시간으로 새로운 데이터가
            // 갱신 되지않는 다는 점이지만 혼자고민임을 감안하면 새로운 데이터가 실시간으로 들어올일도 없고
            // 나중에 정말 필요하다면 SwipeRefresh 추가하는 방식도 괜찮을 듯
            //storageAloneViewModel.getAloneList()
            val newList: List<WorryListResDto.Data> =
                if (isSolved == 0) storageAdapter.currentList.sortedBy { it.finalOption != null } // 고민중으로 정렬
                else storageAdapter.currentList.sortedBy { it.finalOption == null } // 고민완료순으로 정렬

            storageAdapter.submitList(newList) {
                binding.rvPosts.scrollToPosition(0)
            }
        }
        storageAloneViewModel.aloneData.observe(viewLifecycleOwner) { dataList ->
            storageAdapter.submitList(dataList)
            Timber.e(dataList.toString())
            binding.rvPosts.smoothScrollToPosition(0)
        }
    }

    private fun onClickToggleBtn(storageAdapter: StorageAdapter) {
        binding.tbToggle.setOnClickListener {
            if (binding.tbToggle.isChecked) { // 고민중이면
                storageAloneViewModel.isSolved.value = 0
            } else storageAloneViewModel.isSolved.value = 1
            Timber.e(storageAloneViewModel.isSolved.value.toString())
        }
    }
}
