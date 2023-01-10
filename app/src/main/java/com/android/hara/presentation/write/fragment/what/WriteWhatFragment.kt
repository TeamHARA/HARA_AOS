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
import com.android.hara.presentation.util.setBold
import com.android.hara.presentation.write.WriteViewModel

class WriteWhatFragment :
    BindingFragment<FragmentWriteWhatBinding>(R.layout.fragment_write_what) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private val whatViewModel: WhatFragViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setNavigation(view)
        onClickNextBtn()
        addObserve()
        binding.tvWriteWhatQuestion.setBold(
            requireContext(),
            0,
            2,
            this.getString(R.string.write_title_question)
        )


    }

    private fun setViewModel() {
        binding.vm = whatViewModel
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
        if (whatViewModel.enabled.value!!) {
            binding.ibWriteNextButtonOn.visibility = View.VISIBLE
            binding.ibWriteNextButtonOff.visibility = View.INVISIBLE
        } else {
            binding.ibWriteNextButtonOn.visibility = View.INVISIBLE
            binding.ibWriteNextButtonOff.visibility = View.VISIBLE
        }
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
