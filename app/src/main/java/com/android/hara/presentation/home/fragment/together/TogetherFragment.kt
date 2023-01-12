package com.android.hara.presentation.home.fragment.together

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.android.hara.R
import com.android.hara.databinding.FragmentTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.detail.DetailWithActivity
import com.android.hara.presentation.home.fragment.together.adapter.CategoryAdapter
import com.android.hara.presentation.home.fragment.together.adapter.PostAdapter
import com.android.hara.presentation.home.fragment.together.model.SimpleModel
import com.android.hara.presentation.home.fragment.together.viewmodel.TogetherFragmentViewModel
import com.android.hara.presentation.home.viewmodel.HomeViewModel
import com.android.hara.presentation.util.makeSnackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class TogetherFragment : BindingFragment<FragmentTogetherBinding>(R.layout.fragment_together) {

    companion object {
        private lateinit var recyclerView: RecyclerView

        fun setScroll() {
            //recyclerView.scrollToPosition(0)
            recyclerView.smoothScrollToPosition(0)
        }

    }

    private val category: Array<String>
        get() = resources.getStringArray(R.array.category_array)

    private var list = arrayListOf<SimpleModel>()
    private val homeVm by viewModels<HomeViewModel>()
    private lateinit var postAdapter: PostAdapter
    private val togetherViewModel: TogetherFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.clear() // (없으면 카테고리 무한 증식) 나중에 무조건 수정하기
        init()
        setCategoryRecycler()
        setPostAdapter()
        addObserve()
    }// fun onViewCreated()


    private fun init() {
        recyclerView = binding.rvTogetherPost

        binding.swipeRefreash.setOnRefreshListener { /* swipe 시 진행할 동작 */
            recyclerView.smoothScrollToPosition(0)
            homeVm.homeVmGetAllPost(homeVm.selCat.value ?: 0)
        }
    }

    private fun setCategoryRecycler() {
        // [1] recycler view - adapter 연결: 상단 카테고리 목록 [by 유진]
        for (i in 0..7) {
            list.add(SimpleModel(title = category[i], isSelected = false))
        }

        val categoryAdapter = CategoryAdapter(requireContext(), list) { num ->
            homeVm.changeSelCatNum(num)
        }.apply {
            setOnItemClickListener(object : CategoryAdapter.OnItemClickListener {
                override fun onItemClick(item: SimpleModel, position: Int) {
                    Timber.e(item.title) // 카테고리가 클릭되면 '전체', '일상' 등이 찍힌다
                    //recyclerView.scrollToPosition(0)
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

    private fun setPostAdapter() {
        postAdapter = PostAdapter(
            {
                val intent = Intent(requireContext(), DetailWithActivity::class.java)
                intent.putExtra("worryId", it)
                startActivity(intent)
            },
            { postId, optId -> homeVm.changeSelPostAndOptId(postId, optId) },
            { homeVm.changeBtnVal() },
            { homeVm.getOptVoteRate() }
        )
        binding.rvTogetherPost.adapter = postAdapter

        // private val voteResult: h
        // { homeVm.voteResult.value },
    }

    private fun addObserve() {
        // [1] homeVm의 selected category number 값이 변하는지 관찰
        homeVm.selCat.observe(viewLifecycleOwner) {
            Timber.e(it.toString())
            homeVm.homeVmGetAllPost(it)
        }

        // [2] recycler view - adapter 연결: 고민글 목록 [by 수현]
        homeVm.catAllPostResult.observe(viewLifecycleOwner) {
            postAdapter.submitList(it.data) {
                binding.rvTogetherPost.scrollToPosition(0)
            }
        }

        // [2] homeVm의 btn이 변하는지 관찰
        homeVm.btnSel.observe(viewLifecycleOwner) {
            Timber.e("통신 이 값 보내는디!! " + homeVm.getPostId().toString() + homeVm.getOptId().toString())
            homeVm.homeVmPostVote(homeVm.getPostId(), homeVm.getOptId())
        }

        homeVm.suceess.observe(viewLifecycleOwner) {
            Timber.e("refresh")
            binding.swipeRefreash.isRefreshing = false // 서버통신 완료시 리프레시 중단
            if (!it) {
                binding.root.makeSnackbar(getString(R.string.server_connet_fail))
                //실패 시 스낵바, 성공 시 게시물 목록 value 갱신 -> 위의 observer에서 자동 갱신
            }
        }
    }

} // fun onViewCreated()


/*
// n번째 옵션이 선택되면 PostViewModel 안의 sNum의 value가 n으로 바뀐다
private fun changeVmSnum(n: Int) { // n이 선택된 상태
    // 1) n이 클릭되면: n만 비활성화돼야 해
    if (postVm.sNum.value == n) postVm.sNum.value = 0
    // 2) n이 클릭되면: n만 활성화돼야 해
    else postVm.sNum.value = n
}
*/
