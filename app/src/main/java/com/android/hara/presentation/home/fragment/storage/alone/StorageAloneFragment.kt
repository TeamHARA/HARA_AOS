package com.android.hara.presentation.home.fragment.storage.alone

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageSelfBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.home.fragment.storage.StorageAdapter
import com.android.hara.presentation.home.fragment.storage.alone.StorageAloneViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class StorageAloneFragment :
    BindingFragment<FragmentStorageSelfBinding>(R.layout.fragment_storage_self) {
    private val storageAloneViewModel: StorageAloneViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storageAdapter = StorageAdapter() {
            Log.d("TEST", it)
        }
        binding.rvPosts.adapter = storageAdapter
        storageAloneViewModel.getAloneList(0)
        storageAloneViewModel.aloneData.observe(viewLifecycleOwner) { dataList ->
            storageAdapter.submitList(dataList)
            Timber.e(dataList.toString())
        }
    }
}
