package com.android.hara.presentation.home.fragment.together

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.databinding.FragmentTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.home.fragment.together.model.TogetherPostData
import com.android.hara.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TogetherFragment : BindingFragment<FragmentTogetherBinding>(R.layout.fragment_together) {

    private val category: Array<String>
        get() = resources.getStringArray(R.array.category_array)
    private var list = arrayListOf<SimpleModel>()

    // 더미 데이터 - 서버 통신 성공 시 삭제 예정
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

    private val homeVm by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // [1] recycler view - adapter 연결: 상단 카테고리 목록 [by 유진]
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

        // [2] recycler view - adapter 연결: 고민글 목록 [by 수현]
        val postAdapter = PostAdapter { allPostResDto, int -> }

        homeVm.catAllPostResult.observe(viewLifecycleOwner) {
            binding.rvTogetherPost.adapter = postAdapter
            postAdapter.submitList(it.data)
        }

    } // fun onViewCreated()
}

    /*
    // n번째 옵션이 선택되면 PostViewModel 안의 sNum의 value가 n으로 바뀐다
    private fun changeVmSnum(n: Int) { // n이 선택된 상태
        // 1) n이 클릭되면: n만 비활성화돼야 해
        if (postVm.sNum.value == n) postVm.sNum.value = 0
        // 2) n이 클릭되면: n만 활성화돼야 해
        else postVm.sNum.value = n
    }
    */
