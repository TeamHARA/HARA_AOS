package com.android.hara.presentation.write

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteProsconsBinding
import com.android.hara.presentation.base.BindingFragment

class WriteProsconsFragment :
    BindingFragment<FragmentWriteProsconsBinding>(R.layout.fragment_write_proscons) {
    lateinit var navController: NavController
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
        binding.ibWriteProsconsNextButton.setOnClickListener {
            navController.navigate(R.id.action_writeProsconsFragment_to_writeCategoryFragment)
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteProsconsBackButton.setOnClickListener {
            navController.navigateUp()
        }
    }
}
