package com.android.hara.presentation.home.fragment.together

import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.hara.R
import com.android.hara.databinding.FragmentTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TogetherFragment : BindingFragment<FragmentTogetherBinding>(R.layout.fragment_together) {

    private val category: Array<String>
        get() = resources.getStringArray(R.array.category_array)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(requireContext()) {
            Log.d("TEST", it)
        }
        binding.rvTogetherCategory.adapter = categoryAdapter
        categoryAdapter.submitList(category.toList()) // 데이터를 넣어준다 (업데이트할 때에도)
    }
}
