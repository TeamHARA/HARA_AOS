package com.android.hara.presentation.write

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteTitleBinding
import com.android.hara.presentation.base.BindingFragment

class WriteTitleFragment : BindingFragment<FragmentWriteTitleBinding>(R.layout.fragment_write_title) {
    lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        onClickNextBtn()
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteNextButton.setOnClickListener {
            navController.navigate(R.id.action_writeTitleFragment_to_writeContentFragment)
        }
    }
}
