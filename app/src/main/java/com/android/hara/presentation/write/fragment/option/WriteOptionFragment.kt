package com.android.hara.presentation.write.fragment.option

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteOptionBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.write.WriteViewModel
import timber.log.Timber

class WriteOptionFragment :
    BindingFragment<FragmentWriteOptionBinding>(R.layout.fragment_write_option) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private val optionFragViewModel: OptionFragViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        setViewModel()
        //setOptionVisibility()
        addObserve()
        onClickNextBtn()
        onClickBackBtn()

//        binding.option3.ibOptionDeleteButton.setOnClickListener {
//            binding.option3.root.visibility
//        }
    }

    private fun setViewModel() {
        binding.vm = optionFragViewModel
    }

    private fun addObserve() {
//        optionFragViewModel.optionNum.observe(viewLifecycleOwner) { optionNum ->
//            setOptionLayout(optionNum)
//            setNextBtn()
//        }
//        optionFragViewModel.string1.observe(viewLifecycleOwner) { string1 ->
//            setNextBtn()
//            Timber.e(string1)
//        }
//        optionFragViewModel.string2.observe(viewLifecycleOwner) { string2 ->
//            setNextBtn()
//            Timber.e(string2)
//        }
//        optionFragViewModel.string3.observe(viewLifecycleOwner) { string3 ->
//            setNextBtn()
//            Timber.e(string3)
//        }
//        optionFragViewModel.string4.observe(viewLifecycleOwner) { string4 ->
//            setNextBtn()
//            Timber.e(string4)
//        }
    }

//    private fun setOptionVisibility() {
//        with(binding) {
//            option3.ibOptionDeleteButton.visibility = View.VISIBLE
//            option4.ibOptionDeleteButton.visibility = View.VISIBLE
//        }
//    }
//
//    private fun setOptionLayout(optionNum: Int?) {
//        when (optionNum) {
//            2 -> {
//                with(binding) {
//                    option3.root.visibility = View.GONE
//                    option4.root.visibility = View.GONE
//                }
//            }
//            3 -> {
//                with(binding) { // 여기가 문제
//                    option3.root.visibility = View.VISIBLE
//                    option4.root.visibility = View.GONE
//                    btnOptionAddButton.visibility = View.VISIBLE
//                }
//            }
//            4 -> {
//                with(binding) {
//                    option4.root.visibility = View.VISIBLE
//                    btnOptionAddButton.visibility = View.GONE
//                }
//            }
//        }
//    }

    private fun setNextBtn() {
        optionFragViewModel.setEnable()
        if (optionFragViewModel.enabled.value!!) {
            binding.ibWriteOptionNextButtonOn.visibility = View.VISIBLE
            binding.ibWriteOptionNextButtonOff.visibility = View.INVISIBLE
        } else {
            binding.ibWriteOptionNextButtonOn.visibility = View.INVISIBLE
            binding.ibWriteOptionNextButtonOff.visibility = View.VISIBLE
        }
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteOptionNextButtonOn.setOnClickListener {
            navController.navigate(R.id.action_writeOptionFragment_to_writeProsconsFragment)
            writeViewModel.addProgress()
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteOptionBackButton.setOnClickListener {
            navController.navigateUp()
            writeViewModel.subProgress()
        }
    }
}
