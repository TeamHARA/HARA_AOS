package com.android.hara.presentation.write.fragment.what

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TypefaceSpan
import android.view.View
import androidx.core.content.res.ResourcesCompat
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
    private val whatViewModel: WhatFragViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setNavigation(view)
        onClickNextBtn()
        addObserve()

        // 함수 분리
//        val myTypeface = Typeface.create(
//            ResourcesCompat.getFont(requireContext(), R.font.pretendard_medium),
//            Typeface.NORMAL
//        )
//        val cafeTypeface = Typeface.create(
//            ResourcesCompat.getFont(requireContext(), R.font.cafe24_ssurround),
//            //Bold Type font로 나중에 받아서 넣어줄것
//            Typeface.NORMAL
//        )
//        val string = SpannableString(R.string.write_title_question.toString())
//        string.setSpan(TypefaceSpan(cafeTypeface), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        string.setSpan(TypefaceSpan(myTypeface), 3, string.length-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        //각각 인덱스 계산해서 넣어줄 것
//        binding.tvWriteWhatQuestion.text = string
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
            setNextButton()
            setTitleLength()
        }
        whatViewModel.content.observe(viewLifecycleOwner) {
            setNextButton()
            setContentLength()
        }
    }

    private fun setNextButton() {
        whatViewModel.setNextBtn()
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
