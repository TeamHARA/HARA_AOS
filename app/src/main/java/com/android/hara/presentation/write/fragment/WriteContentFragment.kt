package com.android.hara.presentation.write.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteContentBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.write.WriteViewModel

class WriteContentFragment :
    BindingFragment<FragmentWriteContentBinding>(R.layout.fragment_write_content) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private val contentViewModel: ContentFragViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setNavigation(view)
        onClickNextBtn()
        onClickBackBtn()
        setNextBtn()
    }

    private fun setViewModel() {
        binding.vm = contentViewModel
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteContentNextButtonOn.setOnClickListener {
            navController.navigate(R.id.action_writeContentFragment_to_writeOptionFragment)
            writeViewModel.addProgress()
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteContentBackButton.setOnClickListener {
            navController.navigateUp()
            writeViewModel.subProgress()
        }
    }

    private fun setNextBtn() {
        contentViewModel.answer.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) {
                binding.ibWriteContentNextButtonOn.visibility = View.VISIBLE
                binding.ibWriteContentNextButtonOff.visibility = View.INVISIBLE
            } else {
                binding.ibWriteContentNextButtonOn.visibility = View.INVISIBLE
                binding.ibWriteContentNextButtonOff.visibility = View.VISIBLE
            }
        }
    }
}
