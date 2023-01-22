package com.android.hara.presentation.home.fragment.storage.with

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.data.model.response.WorryListResDto
import com.android.hara.databinding.FragmentStorageTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.detail.DetailWithActivity
import com.android.hara.presentation.home.fragment.storage.StorageAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class StorageWithFragment :
    BindingFragment<FragmentStorageTogetherBinding>(R.layout.fragment_storage_together) {
    private val storageWithViewModel: StorageWithViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storageAdapter = StorageAdapter() {
            val intent = Intent(requireContext(), DetailWithActivity::class.java)
            intent.putExtra("detailData", it)
            startActivity(intent)
        }
        binding.rvPosts.adapter = storageAdapter
        storageWithViewModel.getWithList()

        addObserve(storageAdapter)
        onClickToggleBtn()
    }

    private fun addObserve(storageAdapter: StorageAdapter) {
        storageWithViewModel.isSolved.observe(viewLifecycleOwner) { isSolved ->
            //storageWithViewModel.getWithList()
            // 바뀐이유는 StorageAloneFragment에 나와있음
            val newList: List<WorryListResDto.Data> =
                if (isSolved == 0) storageAdapter.currentList.sortedBy { it.finalOption != null } // 고민중으로 정렬
                else storageAdapter.currentList.sortedBy { it.finalOption == null } // 고민완료순으로 정렬

            storageAdapter.submitList(newList) {
                binding.rvPosts.scrollToPosition(0)
            }
        }
        storageWithViewModel.withData.observe(viewLifecycleOwner) { dataList ->
            storageAdapter.submitList(dataList)
            Timber.e(dataList.toString())
            binding.rvPosts.smoothScrollToPosition(0) // TODO: 광클방지
        }
    }

    private fun onClickToggleBtn() {
        binding.tbToggle.setOnClickListener {
            if (binding.tbToggle.isChecked) { // 고민중이면
                storageWithViewModel.isSolved.value = 0
            } else storageWithViewModel.isSolved.value = 1
            Timber.e(storageWithViewModel.isSolved.value.toString())
        }
    }
}
