package com.android.hara.presentation.home.fragment.together

import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.hara.R
import com.android.hara.databinding.FragmentTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.home.fragment.together.model.TogetherPostData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TogetherFragment : BindingFragment<FragmentTogetherBinding>(R.layout.fragment_together) {

    private val category: Array<String>
        get() = resources.getStringArray(R.array.category_array)

    private val tempList = listOf<TogetherPostData>(
        TogetherPostData("일상", "2022.11.17", "여기는 제목입니다",
            "여기는 본문을 쓰는 곳입니다 근데 이게 맞아요?", 20,
            "옵션1: 이곳은 옵션 1에 대해", "옵션2: 이곳은 옵션 2에 대해",
            "옵션3: 이곳은 옵션 3에 대해", "옵션4: 이곳은 옵션 4에 대해"
        ),
        TogetherPostData("친구", "2042.01.13", "울랄라 울랄라",
            "여기는 본문을 쓰는 곳입니다 근데 이게 맞아요?", 20,
            "옵션1: 이곳은 옵션 1에 대해", "옵션2: 이곳은 옵션 2에 대해",
            "옵션3: 이곳은 옵션 3에 대해", "옵션4: 이곳은 옵션 4에 대해"
        ),
        TogetherPostData("연애", "2032.12.01", "오호라",
            "여기는 본문을 쓰는 곳입니다 근데 이게 맞아요?", 20,
            "옵션1: 이곳은 옵션 1에 대해", "옵션2: 이곳은 옵션 2에 대해",
            "옵션3: 이곳은 옵션 3에 대해", "옵션4: 이곳은 옵션 4에 대해"
        ),
        TogetherPostData("취업", "2052.06.24", "저 지금 졸린데 어떡하나욤",
            "여기는 본문을 쓰는 곳입니다 근데 이게 맞아요?", 20,
            "옵션1: 이곳은 옵션 1에 대해", "옵션2: 이곳은 옵션 2에 대해",
            "옵션3: 이곳은 옵션 3에 대해", "옵션4: 이곳은 옵션 4에 대해"
        ),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // [1] recycler view - adapter 연결: 상단 카테고리 목록 [by 유진?]
        val categoryAdapter = CategoryAdapter(requireContext()) {
            Log.d("TEST", it)
        }
        binding.rvTogetherCategory.adapter = categoryAdapter
        categoryAdapter.submitList(category.toList()) // 데이터를 넣어준다 (업데이트할 때에도)

        // [2] recycler view - adapter 연결: 고민글 목록 [by 수현]
        val postAdapter = PostAdapter() {
            Log.d("TEST", it)
        }
        binding.rvTogetherPost.adapter = postAdapter
        postAdapter.submitList(tempList)
    }
}
