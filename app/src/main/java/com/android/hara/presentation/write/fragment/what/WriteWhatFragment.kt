package com.android.hara.presentation.write.fragment.what

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteWhatBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.write.WriteViewModel

class WriteWhatFragment :
    BindingFragment<FragmentWriteWhatBinding>(R.layout.fragment_write_what) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private val titleViewModel: WhatFragViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setNavigation(view)
        onClickNextBtn()
//        setNextBtn()
    }

    private fun setViewModel() {
        binding.vm = titleViewModel
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteNextButtonOn.setOnClickListener {
            navController.navigate(R.id.action_writeWhatFragment_to_writeOptionFragment)
            writeViewModel.addProgress()
        }
    }

//    private fun setNextBtn() {
//        titleViewModel.answer.observe(viewLifecycleOwner) {
//            if (!it.isNullOrBlank()) {
//                binding.ibWriteNextButtonOn.visibility = View.VISIBLE
//                binding.ibWriteNextButtonOff.visibility = View.INVISIBLE
//            } else {
//                binding.ibWriteNextButtonOn.visibility = View.INVISIBLE
//                binding.ibWriteNextButtonOff.visibility = View.VISIBLE
//            }
//        }
//    }
}
