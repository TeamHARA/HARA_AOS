package com.android.hara.presentation.write.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteTitleBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.write.WriteViewModel

class WriteTitleFragment :
    BindingFragment<FragmentWriteTitleBinding>(R.layout.fragment_write_title) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private val titleViewModel: TitleFragViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setNavigation(view)
        onClickNextBtn()
        setNextBtn()
    }

    private fun setViewModel() {
        binding.vm = titleViewModel
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteNextButtonOn.setOnClickListener {
            navController.navigate(R.id.action_writeTitleFragment_to_writeContentFragment)
            writeViewModel.addProgress()
        }
    }

    private fun setNextBtn() {
        titleViewModel.answer.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) {
                binding.ibWriteNextButtonOn.visibility = View.VISIBLE
                binding.ibWriteNextButtonOff.visibility = View.INVISIBLE
            } else {
                binding.ibWriteNextButtonOn.visibility = View.INVISIBLE
                binding.ibWriteNextButtonOff.visibility = View.VISIBLE
            }
        }
    }
}
