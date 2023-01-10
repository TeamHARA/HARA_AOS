package com.android.hara.presentation.home.fragment.storage.alone

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageSelfBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.home.fragment.storage.StorageAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class StorageAloneFragment :
    BindingFragment<FragmentStorageSelfBinding>(R.layout.fragment_storage_self) {

    private val storageAloneViewModel: StorageAloneViewModel by viewModels()

//    private val dummyList = listOf<StorageData>(
//        StorageData("일상", "우리집에 왜왔니 왜왔니 왜왔니", "2023.12.25", true),
//        StorageData("사랑", "카레는 맛있어 나는 안 졸려", "2023.12.25", true),
//        StorageData("일상", "우리집에 호이호이", "2023.12.25", true),
//        StorageData("시하", "우리집에 갤럭시", "2023.12.25", true),
//        StorageData("하시", "나나나나나나나나나나나마", "2023.12.25", false),
//        StorageData("사과", "울라울라울라울라울라울라울라울라", "2023.12.25", false),
//        StorageData("귤귤", "노트북노트북노트북노트북노트북노트북", "2023.12.25", false),
//        StorageData("안드", "노틀담의꼽추노틀담의꼽추", "2023.12.25", false)
//    )

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
