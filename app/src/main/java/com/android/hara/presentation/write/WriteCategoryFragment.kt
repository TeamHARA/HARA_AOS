package com.android.hara.presentation.write

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteCategoryBinding
import com.android.hara.presentation.base.BindingFragment

class WriteCategoryFragment :
    BindingFragment<FragmentWriteCategoryBinding>(R.layout.fragment_write_category) {
    lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        onClickBackBtn()
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickBackBtn() {
        binding.ibWriteCategoryBackButton.setOnClickListener {
            navController.navigateUp()
        }
    }
}
