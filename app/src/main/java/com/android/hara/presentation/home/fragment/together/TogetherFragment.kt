package com.android.hara.presentation.home.fragment.together

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import com.android.hara.R
import com.android.hara.databinding.FragmentTogetherBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TogetherFragment : BindingFragment<FragmentTogetherBinding>(R.layout.fragment_together) {

    private val category: Array<String>
        get() = resources.getStringArray(R.array.category_array)

    private var list = arrayListOf<SimpleModel>()
    private val homeVm by viewModels<HomeViewModel>()
    private lateinit var postAdapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postAdapter = PostAdapter(
            { postId, optId -> homeVm.changeSelPostAndOptId(postId, optId) },
            { homeVm.changeBtnVal() },
            { requireContext().getDrawable(R.drawable.shape_rectangle_gray3_fill_8)!! },
            { requireContext().getColor(R.color.white) }
        )
        binding.rvTogetherPost.adapter = postAdapter

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
                }
            })
        }

        // [1] homeVm의 selected category number 값이 변하는지 관찰
        homeVm.selCat.observe(viewLifecycleOwner) {
            Timber.e(it.toString())
            homeVm.homeVmGetAllPost(it)
        }

        // [2] recycler view - adapter 연결: 고민글 목록 [by 수현]
        homeVm.catAllPostResult.observe(viewLifecycleOwner) {
            postAdapter.submitList(it.data)
        }

        // [2] homeVm의 btn이 변하는지 관찰
        homeVm.btnSel.observe(viewLifecycleOwner) {
            Timber.e("hello", homeVm.getPostId(), homeVm.getOptId())
            homeVm.homeVmPostVote(homeVm.getPostId(), homeVm.getOptId())
        }

        binding.rvTogetherCategory.adapter = categoryAdapter
        binding.rvTogetherCategory.setHasFixedSize(true)
        binding.rvTogetherCategory.itemAnimator = null

    } // fun onViewCreated()

}