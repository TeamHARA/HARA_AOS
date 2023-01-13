package com.android.hara.presentation.write.fragment.how

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteHowBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.custom.DecisionDialog
import com.android.hara.presentation.custom.model.DialogData
import com.android.hara.presentation.util.setOnSingleClickListener
import com.android.hara.presentation.write.WriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteHowFragment :
    BindingFragment<FragmentWriteHowBinding>(R.layout.fragment_write_how) {
    private lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()
    private val howFragViewModel: HowFragViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        showDialog()
        onClickBackBtn()
        setViewModel()
        setWorryBtn()
        setWithBtn()
    }

    private fun showDialog() {
        binding.btnWriteContentUploadButton.setOnSingleClickListener {
            writeViewModel.isWith = howFragViewModel.isWithSelected.value!!
            DecisionDialog(
                requireContext(),
                DialogData(
                    getString(R.string.dialog_upload_title),
                    getString(R.string.dialog_upload_warn),
                    getString(R.string.cancel),
                    getString(R.string.dialog_upload)
                )
            ) { writeViewModel.postWorry() }.showDialog()
        }
    }

    private fun setWorryBtn() {
        binding.clCategoryWorryAloneButton.setOnClickListener {
            howFragViewModel.isAloneSelected.value = !howFragViewModel.isAloneSelected.value!!
            howFragViewModel.isWithSelected.value = false
            setUploadBtn() // 버튼 누를 때마다 호출해줘야 함!
        }
    }

    private fun setWithBtn() {
        binding.clCategoryWorryWithButton.setOnClickListener {
            howFragViewModel.isWithSelected.value = !howFragViewModel.isWithSelected.value!!
            howFragViewModel.isAloneSelected.value = false
            setUploadBtn()
        }
    }

    private fun setUploadBtn() {
        howFragViewModel.enabled.value =
            howFragViewModel.isWithSelected.value!! || howFragViewModel.isAloneSelected.value!!
    }

    private fun setViewModel() {
        binding.vm = howFragViewModel
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickBackBtn() {
        binding.ibWriteContentBackButton.setOnClickListener {
            navController.popBackStack()
            writeViewModel.subProgress()
        }
    }
}
