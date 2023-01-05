package com.android.hara.presentation.write.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteTitleBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.write.WriteViewModel

class WriteTitleFragment : BindingFragment<FragmentWriteTitleBinding>(R.layout.fragment_write_title) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()

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
            writeViewModel.addProgress()
        }
    }
}
