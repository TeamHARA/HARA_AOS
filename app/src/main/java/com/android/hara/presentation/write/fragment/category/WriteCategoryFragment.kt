package com.android.hara.presentation.write.fragment.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteCategoryBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.custom.PickerBottomSheetDialog
import com.android.hara.presentation.util.setOnSingleClickListener
import com.android.hara.presentation.write.WriteViewModel

class WriteCategoryFragment :
    BindingFragment<FragmentWriteCategoryBinding>(R.layout.fragment_write_category) {
    private lateinit var navController: NavController
    private val categoryViewModel: CategoryFregViewModel by viewModels() // 프래그먼트 단일 뷰모델
    private val writeViewModel: WriteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        onClickNextBtn()
        onClickBackBtn()
        setClickListener()
        addObserve()
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun setClickListener() {
        binding.clCategorySelectLayout.setOnSingleClickListener {
            PickerBottomSheetDialog() { categoryViewModel.setCategory(it) }.show(
                childFragmentManager,
                "picker"
            )
        }
        binding.tvSelectedCategory.setOnSingleClickListener {
            PickerBottomSheetDialog() { categoryViewModel.setCategory(it) }.show(
                childFragmentManager,
                "picker"
            )
        }
    }

    private fun addObserve() {
        categoryViewModel.category.observe(viewLifecycleOwner) {
            with(binding) {
                category = it
                writeViewModel.categoty = it // ViewModel에 set 해주기
                tvSelectedCategory.visibility = View.VISIBLE
                clCategorySelectLayout.visibility = View.GONE
                ibWriteCategoryNextButtonOn.visibility = View.VISIBLE
                ibWriteCategoryNextButtonOff.visibility = View.GONE
            }
        }
    }

    private fun onClickNextBtn() {
        binding.ibWriteCategoryNextButtonOn.setOnSingleClickListener {
            navController.navigate(R.id.action_writeCategoryFragment_to_writeHowFragment)
            writeViewModel.addProgress()
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteCategoryBackButton.setOnSingleClickListener {
            navController.navigate(R.id.action_writeCategoryFragment_to_writeProsconsFragment)
            writeViewModel.subProgress()
        }
    }
}
