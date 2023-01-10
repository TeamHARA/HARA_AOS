package com.android.hara.presentation.home.fragment.storage.with

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageTogetherBinding
import com.android.hara.presentation.base.BindingFragment
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
            Log.d("TEST", it)
        }
        binding.rvPosts.adapter = storageAdapter
        storageWithViewModel.getWithList(0)
        storageWithViewModel.withData.observe(viewLifecycleOwner) { dataList ->
            storageAdapter.submitList(dataList)
            Timber.e(dataList.toString())
        }
    }
}
