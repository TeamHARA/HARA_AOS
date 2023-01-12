package com.android.hara.presentation.write.fragment.what

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteWhatBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.util.setOnSingleClickListener
import com.android.hara.presentation.write.WriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteWhatFragment :
    BindingFragment<FragmentWriteWhatBinding>(R.layout.fragment_write_what) {
    private lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private val whatViewModel: WhatFragViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setNavigation(view)
        onClickNextBtn()
        addObserve()

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })

    }


    private fun setViewModel() {
        binding.vm = whatViewModel
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteNextButtonOn.setOnSingleClickListener {
            navController.navigate(R.id.action_writeWhatFragment_to_writeOptionFragment)
            writeViewModel.addProgress()
            writeViewModel.title = binding.etWriteWhatAnswer.text.toString()
            writeViewModel.content = binding.etWriteContentAnswer.text.toString()
        }
    }

    private fun addObserve() {
        whatViewModel.title.observe(viewLifecycleOwner) {
            setNextBtn()
            setTitleLength()
        }
        whatViewModel.content.observe(viewLifecycleOwner) {
            setNextBtn()
            setContentLength()
        }
    }

    private fun setNextBtn() {
        whatViewModel.setEnabled()
        binding.ibWriteNextButtonOn.isVisible = whatViewModel.enabled.value!!
        binding.ibWriteNextButtonOff.isVisible = !(whatViewModel.enabled.value!!)
    }

    private fun setTitleLength() {
        binding.whatTitleLength.text = String.format(
            getString(R.string.write_title_length),
            whatViewModel.title.value!!.length
        )
    }

    private fun setContentLength() {
        binding.whatContentLength.text = String.format(
            getString(R.string.write_content_length),
            whatViewModel.content.value!!.length
        )
    }
}
