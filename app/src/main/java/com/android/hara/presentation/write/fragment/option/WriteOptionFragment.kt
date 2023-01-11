package com.android.hara.presentation.write.fragment.option

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
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
    private var list = mutableListOf<OptionData>(
        OptionData(0, "", "".toUri(), true),
        OptionData(1, "", "".toUri(), true),
        OptionData(99, "", "".toUri(), false)
    )

    private lateinit var adapter: WriteOptionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WriteOptionAdapter({ addItem() }, {
            binding.ibWriteOptionNextButtonOff.isVisible = !it
            binding.ibWriteOptionNextButtonOn.isVisible = it
        })
        binding.rcvOptions.adapter = adapter
        adapter.submitList(list)
        setNavigation(view)
        setViewModel()
        addObserve()
        onClickNextBtn()
        onClickBackBtn()
        binding.tvWriteOptionQuestion.setBold(
            requireContext(),
            0,
            8,
            requireContext().getString(R.string.write_option_question)
        )
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
