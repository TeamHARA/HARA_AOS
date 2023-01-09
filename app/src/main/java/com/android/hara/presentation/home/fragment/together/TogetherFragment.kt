package com.android.hara.presentation.home.fragment.together

import android.os.Bundle
import android.view.View
import com.android.hara.R
import com.android.hara.databinding.FragmentTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TogetherFragment : BindingFragment<FragmentTogetherBinding>(R.layout.fragment_together) {

    private val category: Array<String>
        get() = resources.getStringArray(R.array.category_array)
    private var list = arrayListOf<SimpleModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (i in 0..7) {
            list.add(SimpleModel(title = category[i], isSelected = false))
        }

        val categoryAdapter = CategoryAdapter(requireContext(), list).apply {
            setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
                override fun onItemClick(item: SimpleModel, position: Int) {
                    Timber.e(item.title)
                }
            })
        }
        binding.rvTogetherCategory.adapter = categoryAdapter
//        categoryAdapter.submitList(category.toList()) // 데이터를 넣어준다 (업데이트할 때에도)
        binding.rvTogetherCategory.setHasFixedSize(true)
        binding.rvTogetherCategory.itemAnimator = null
    }
}
