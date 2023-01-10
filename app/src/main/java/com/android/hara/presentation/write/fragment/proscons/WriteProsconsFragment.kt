package com.android.hara.presentation.write.fragment.proscons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteProsconsBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.util.setBold
import com.android.hara.presentation.write.WriteViewModel

class WriteProsconsFragment :
    BindingFragment<FragmentWriteProsconsBinding>(R.layout.fragment_write_proscons) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        onClickNextBtn()
        onClickBackBtn()
        binding.tvWriteProsconsQuestion.setBold(
            requireContext(),
            5,
            8,
            requireContext().getString(R.string.write_proscons_question)
        )
        val list =
            listOf(binding.proscons1, binding.proscons2, binding.proscons3, binding.proscons4)
        writeViewModel.titleList.forEachIndexed { index, s ->
            list[index].visible = s == ""
            list[index].title = s
        }

    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteProsconsNextButton.setOnClickListener {
            navController.navigate(R.id.action_writeProsconsFragment_to_writeCategoryFragment)
            writeViewModel.addProgress()
        }
    }

    private fun onClickBackBtn() {
        binding.ibWriteProsconsBackButton.setOnClickListener {
            navController.navigateUp()
            writeViewModel.subProgress()
        }
    }
}
