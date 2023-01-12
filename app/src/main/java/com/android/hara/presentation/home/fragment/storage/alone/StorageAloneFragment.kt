package com.android.hara.presentation.home.fragment.storage.alone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageSelfBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.detail.DetailAloneActivity
import com.android.hara.presentation.home.fragment.storage.StorageAdapter
import com.android.hara.presentation.util.setOnSingleClickListener
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
        onClickToggleBtn()
    }

    private fun addObserve(storageAdapter: StorageAdapter) {
        storageAloneViewModel.aloneData.observe(viewLifecycleOwner) { dataList ->
            storageAdapter.submitList(dataList)
            Timber.e(dataList.toString())
            binding.rvPosts.smoothScrollToPosition(0)
        }
        storageAloneViewModel.isSolved.observe(viewLifecycleOwner) {
            storageAloneViewModel.getAloneList()
        }
    }

    private fun onClickToggleBtn() {
        binding.tbToggle.setOnSingleClickListener {
            if (binding.tbToggle.isChecked) { // 고민중이면
                storageAloneViewModel.isSolved.value = 0
            } else storageAloneViewModel.isSolved.value = 1
            Timber.e(storageAloneViewModel.isSolved.value.toString())
        }
    }
}
