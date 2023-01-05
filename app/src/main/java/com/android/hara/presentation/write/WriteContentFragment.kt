package com.android.hara.presentation.write

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteContentBinding
import com.android.hara.presentation.base.BindingFragment

class WriteContentFragment :
    BindingFragment<FragmentWriteContentBinding>(R.layout.fragment_write_content) {
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
        binding.ibWriteContentNextButton.setOnClickListener {
            navController.navigate(R.id.action_writeContentFragment_to_writeOptionFragment)
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteContentBackButton.setOnClickListener {
            navController.navigateUp()
        }
    }
}
