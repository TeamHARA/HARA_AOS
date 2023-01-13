package com.android.hara.presentation.home.fragment.storage.with

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.detail.DetailWithActivity
import com.android.hara.presentation.home.fragment.storage.StorageAdapter
import com.android.hara.presentation.util.setOnSingleClickListener
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
        storageWithViewModel.withData.observe(viewLifecycleOwner) { dataList ->
            storageAdapter.submitList(dataList)
            Timber.e(dataList.toString())
            binding.rvPosts.smoothScrollToPosition(0) // TODO: 광클방지
        }
        storageWithViewModel.isSolved.observe(viewLifecycleOwner) {
            storageWithViewModel.getWithList()
        }
    }

    private fun onClickToggleBtn() {
        binding.tbToggle.setOnSingleClickListener {
            if (binding.tbToggle.isChecked) { // 고민중이면
                storageWithViewModel.isSolved.value = 0
            } else storageWithViewModel.isSolved.value = 1
            Timber.e(storageWithViewModel.isSolved.value.toString())
        }
    }
}
