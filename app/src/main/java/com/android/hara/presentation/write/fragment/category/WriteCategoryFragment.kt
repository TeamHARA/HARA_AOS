package com.android.hara.presentation.write.fragment.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteCategoryBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.write.WriteViewModel

class WriteCategoryFragment :
    BindingFragment<FragmentWriteCategoryBinding>(R.layout.fragment_write_category) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        onClickNextBtn()
        onClickBackBtn()
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteCategoryNextButtonOn.setOnClickListener {
            navController.navigate(R.id.action_writeCategoryFragment_to_writeHowFragment)
            writeViewModel.addProgress()
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteCategoryBackButton.setOnClickListener {
            navController.navigateUp()
            writeViewModel.subProgress()
        }
    }
}
