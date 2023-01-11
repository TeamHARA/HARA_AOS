package com.android.hara.presentation.write.fragment.proscons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteProsconsBinding
import com.android.hara.databinding.ItemWriteProsconsBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.util.setBold
import com.android.hara.presentation.util.setOnSingleClickListener
import com.android.hara.presentation.write.WriteViewModel
import com.android.hara.presentation.write.fragment.proscons.model.PronsData

class WriteProsconsFragment :
    BindingFragment<FragmentWriteProsconsBinding>(R.layout.fragment_write_proscons) {
    private lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private lateinit var prosList: List<ItemWriteProsconsBinding>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setNavigation(view)
        onClickNextBtn()
        onClickBackBtn()
    }

    private fun init() {
        prosList = listOf(
            binding.proscons1,
            binding.proscons2,
            binding.proscons3,
            binding.proscons4
        )

        writeViewModel.titleList.forEachIndexed { index, s ->
            prosList[index].visible = s == ""
            prosList[index].title = s
        }
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteProsconsNextButton.setOnSingleClickListener {
            val newList = mutableListOf<PronsData>()
            prosList.forEachIndexed { index, itemWriteProsconsBinding ->
                newList.add(
                    PronsData(
                        itemWriteProsconsBinding.etWriteProsAnswer.text.toString(),
                        itemWriteProsconsBinding.etWriteConsAnswer.text.toString()
                    )
                )
            }
            writeViewModel.pronsList.addAll(newList)
            //시점 유의
            navController.navigate(R.id.action_writeProsconsFragment_to_writeCategoryFragment)
            writeViewModel.addProgress()
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteProsconsBackButton.setOnSingleClickListener {
            navController.navigate(R.id.action_writeProsconsFragment_to_writeOptionFragment)
            writeViewModel.subProgress()
        }
    }
}
