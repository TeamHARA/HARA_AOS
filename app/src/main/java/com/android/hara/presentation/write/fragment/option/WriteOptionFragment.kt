package com.android.hara.presentation.write.fragment.option

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.hara.R
import com.android.hara.databinding.FragmentWriteOptionBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.write.WriteViewModel

class WriteOptionFragment :
    BindingFragment<FragmentWriteOptionBinding>(R.layout.fragment_write_option) {
    lateinit var navController: NavController
    private val writeViewModel: WriteViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation(view)
        onClickNextBtn()
        onClickBackBtn()

        val optionAdapter = OptionAdapter(requireContext()) {
            Log.d("TEST", it.id.toString())
        }
        binding.rvWriteWorryOption.adapter = optionAdapter
    }

    private fun setNavigation(view: View) {
        navController = Navigation.findNavController(view)
    }

    private fun onClickNextBtn() {
        binding.ibWriteOptionNextButton.setOnClickListener {
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
