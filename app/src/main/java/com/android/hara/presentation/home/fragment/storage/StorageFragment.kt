package com.android.hara.presentation.home.fragment.storage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageBinding
import com.android.hara.presentation.base.BindingFragment


class StorageFragment : BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStorageOnesec.setOnClickListener {

        }
    }
}