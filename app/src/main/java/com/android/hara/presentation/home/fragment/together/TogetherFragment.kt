package com.android.hara.presentation.home.fragment.together

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.R
import com.android.hara.databinding.FragmentTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.home.fragment.together.model.TogetherPostData
import com.android.hara.presentation.home.fragment.together.viewmodel.TogetherFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class TogetherFragment : BindingFragment<FragmentTogetherBinding>(R.layout.fragment_together) {

    companion object {
        private lateinit var recyclerView: RecyclerView

        fun setScroll() {
            recyclerView.scrollToPosition(0)
        }

    }

    private val category: Array<String>
        get() = resources.getStringArray(R.array.category_array)
    private var list = arrayListOf<SimpleModel>()
    private val togetherViewModle: TogetherFragmentViewModel by viewModels()

    private val tempList = listOf<TogetherPostData>(
        TogetherPostData(
            "일상", "2022.11.17", "여기는 제목입니다",
            "여기는 본문을 쓰는 곳입니다 근데 이게 맞아요?", 20,
            "옵션1: 이곳은 옵션 1에 대해", "옵션2: 이곳은 옵션 2에 대해",
            "옵션3: 이곳은 옵션 3에 대해", "옵션4: 이곳은 옵션 4에 대해"
        ),
        TogetherPostData(
            "친구", "2042.01.13", "울랄라 울랄라",
            "여기는 본문을 쓰는 곳입니다 근데 이게 맞아요?", 20,
            "옵션1: 이곳은 옵션 1에 대해", "옵션2: 이곳은 옵션 2에 대해",
            "옵션3: 이곳은 옵션 3에 대해", "옵션4: 이곳은 옵션 4에 대해"
        ),
        TogetherPostData(
            "연애", "2032.12.01", "오호라",
            "여기는 본문을 쓰는 곳입니다 근데 이게 맞아요?", 20,
            "옵션1: 이곳은 옵션 1에 대해", "옵션2: 이곳은 옵션 2에 대해",
            "옵션3: 이곳은 옵션 3에 대해", "옵션4: 이곳은 옵션 4에 대해"
        ),
        TogetherPostData(
            "취업", "2052.06.24", "저 지금 졸린데 어떡하나욤",
            "여기는 본문을 쓰는 곳입니다 근데 이게 맞아요?", 20,
            "옵션1: 이곳은 옵션 1에 대해", "옵션2: 이곳은 옵션 2에 대해",
            "옵션3: 이곳은 옵션 3에 대해", "옵션4: 이곳은 옵션 4에 대해"
        ),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.clear() // (없으면 카테고리 무한 증식) 나중에 무조건 수정하기
        setCategoryRecycler()
        setPostRecycler()

        binding.swipeRefreash.setOnRefreshListener { /* swipe 시 진행할 동작 */
            //TODO get 서버통신
            /* 업데이트가 끝났음을 알림 */
            Timber.e("ho")
            togetherViewModle.success.value = true
        }

        recyclerView = binding.rvTogetherPost

        togetherViewModle.success.observe(viewLifecycleOwner) {
            if (it) binding.swipeRefreash.isRefreshing = false
        }


    }

    private fun setCategoryRecycler() {
        // [1] recycler view - adapter 연결: 상단 카테고리 목록 [by 유진]
        for (i in 0..7) {
            list.add(SimpleModel(title = category[i], isSelected = false))
        }

        val categoryAdapter = CategoryAdapter(requireContext(), list).apply {
            setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
                override fun onItemClick(item: SimpleModel, position: Int) {
                }
            })
        }
        with(binding.rvTogetherCategory) {
            adapter = categoryAdapter
            setHasFixedSize(true)
            itemAnimator = null
        }
//        categoryAdapter.submitList(category.toList()) // 데이터를 넣어준다 (업데이트할 때에도)
    }

    /*
        [2] recycler view - adapter 연결: 고민글 목록 [by 수현]
     */
    private fun setPostRecycler() {
        val postAdapter = PostAdapter { togetherPostData, int ->

        }
        binding.rvTogetherPost.adapter = postAdapter
        postAdapter.submitList(tempList)
    }
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
