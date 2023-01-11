package com.android.hara.presentation.write.fragment.option

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteOptionBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.util.setBold
import com.android.hara.presentation.util.setOnSingleClickListener
import com.android.hara.presentation.write.WriteViewModel
import com.android.hara.presentation.write.fragment.option.adapter.WriteOptionAdapter
import com.android.hara.presentation.write.fragment.option.model.OptionData

class WriteOptionFragment :
    BindingFragment<FragmentWriteOptionBinding>(R.layout.fragment_write_option) {

    // 나중에  컴패니언 오브젝트 말고 뷰모델 고차함수 넘겨줘서 하자
    // 따라서 onBindViewHolder에서 뷰모델의 리스트 내용에 따라
    // eidtetext의 내용을 바꿔줘야 하는 함수도 넘겨줘야함 ->
    // 대신 실시간 대응이 이루어지지 않게하려면...
    // 프래그먼트의 뷰모델만 만들어 놓고
    // 지금처럼 넥스트 버튼을 눌렀을때 액티비티 뷰모델에 set 해주고
    //onViewCreated 같은 곳에서 액티비티 뷰모델에 따라서 옵션 데이터의 개수를 조정하도록 하자
    // 이때 OptionData는 그냥 뷰홀더 속성만을 관리해주도록 하자
    //
    // 지금당장은 아이템 개수 대응만 해보자

    private lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private lateinit var adapter: WriteOptionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        initAdapter()
        onClickNextBtn()
        onClickBackBtn()
        binding.tvWriteOptionQuestion.setBold(
            requireContext(),
            0,
            8,
            requireContext().getString(R.string.write_option_question)
        )
    }

    private fun initAdapter() {
        /*
        해당 로직들의 이유 : 사용작 선택지를 작성하고 장단점 프래그먼트로 넘어갔다가 다시
        선택지 작성 뷰로 넘어가게 되면 초기 선택지 1 2 + 상태가 아닌 이전에
        사용자가 작성해주었던 상태로 동기화 시켜줘야함 그래서 다음과 같이
        adapter에 있는 선택지의 개수와 뷰모델에 저장되어 있는 데이터를 동기화 시켜주고
        이로인해서 발생되었던 앱 종료현상도 같이 개선
        문제점 : Companion Object가 사용되었고 일부 예외 처리 코드가 일부 있음
         */

        adapter = WriteOptionAdapter({ addItem() }, {
            binding.ibWriteOptionNextButtonOff.isVisible = !it
            binding.ibWriteOptionNextButtonOn.isVisible = it
            // 버튼 활성화 로직 고차함수 넘겨줌
        }) // 먼저 생성해주어야 앱 크래쉬 방지

        WriteOptionAdapter.setAdapterTitleList(writeViewModel.titleList)
        // 현재 어댑터의 리스트를 뷰모델 리스트와 동기화 시켜줌
        val tempList = mutableListOf<OptionData>(
            OptionData(99, false)// +버튼
        )
        WriteOptionAdapter.getAdapterTitleList().forEach {
            if (it != "") tempList.add(OptionData(adapter.currentList.size - 1, true))
        }
        if (tempList.size == 1) { //뷰모델에 아무것도 없는 상태 == 가장 최조 진입시점
            tempList.add(OptionData(0, true))
            tempList.add(OptionData(1, true))
        }
        binding.rcvOptions.adapter = adapter
        adapter.submitList(tempList.sortedBy { !it.veiwType }.toList()) //+ 버튼이 무조건 마지막으로 갈수 있도록 정렬
    }

    private fun addItem() {
        // 리싸이클러뷰 아이템 추가하는 함수
        val newList = adapter.currentList.toMutableList()
        newList.add(OptionData(adapter.currentList.size - 1, true))
        adapter.submitList(newList.sortedBy { !it.veiwType }.toList()) //+ 버튼이 무조건 마지막으로 갈수 있도록 정렬
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteOptionNextButtonOn.setOnSingleClickListener {
            navController.navigate(R.id.action_writeOptionFragment_to_writeProsconsFragment)
            writeViewModel.addProgress()
            writeViewModel.titleList.clear()
            writeViewModel.titleList.addAll(WriteOptionAdapter.getAdapterTitleList())
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteOptionBackButton.setOnSingleClickListener {
            navController.navigateUp()
            writeViewModel.subProgress()
        }
    }
}
