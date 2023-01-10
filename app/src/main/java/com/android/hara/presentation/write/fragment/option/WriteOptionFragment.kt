package com.android.hara.presentation.write.fragment.option

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteOptionBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.util.setBold
import com.android.hara.presentation.write.WriteViewModel
import com.android.hara.presentation.write.fragment.option.adapter.WriteOptionAdapter
import com.android.hara.presentation.write.fragment.option.model.OptionData
import timber.log.Timber

class WriteOptionFragment :
    BindingFragment<FragmentWriteOptionBinding>(R.layout.fragment_write_option) {

    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private val optionFragViewModel: OptionFragViewModel by viewModels()
    private var list = mutableListOf<OptionData>(
        OptionData(0, "", "".toUri(), true),
        OptionData(1, "", "".toUri(), true),
        OptionData(99, "", "".toUri(), false)
    )

    private lateinit var adapter: WriteOptionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WriteOptionAdapter({ addItem() }, {
            Timber.e(it.toString())
            if (it) {
                binding.ibWriteOptionNextButtonOff.visibility = View.GONE
                binding.ibWriteOptionNextButtonOn.visibility = View.VISIBLE
            } else {
                binding.ibWriteOptionNextButtonOff.visibility = View.VISIBLE
                binding.ibWriteOptionNextButtonOn.visibility = View.GONE
            }
        })
        binding.rcvOptions.adapter = adapter
        adapter.submitList(list)
        setNavigation(view)
        setViewModel()
        //setOptionVisibility()
        addObserve()
        onClickNextBtn()
        onClickBackBtn()
        binding.tvWriteOptionQuestion.setBold(
            requireContext(),
            0,
            8,
            requireContext().getString(R.string.write_option_question)
        )

//        binding.option3.ibOptionDeleteButton.setOnClickListener {
//            binding.option3.root.visibility
//        }
    }

    private fun addItem() {
        // 리싸이클러뷰 아이템 추가하는 함수
        val newList = adapter.currentList.toMutableList()
        newList.add(OptionData(adapter.currentList.size - 1, "", "".toUri(), true))
        adapter.submitList(newList.sortedBy { !it.veiwType }.toList()) //+ 버튼이 무조건 마지막으로 갈수 있도록
    }

    private fun setViewModel() {
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
            writeViewModel.titleList.addAll(WriteOptionAdapter.titleList.toMutableList())
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteOptionBackButton.setOnClickListener {
            navController.navigateUp()
            writeViewModel.subProgress()
        }
    }
}
