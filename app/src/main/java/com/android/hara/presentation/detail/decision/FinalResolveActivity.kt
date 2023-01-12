package com.android.hara.presentation.detail.decision

import android.os.Bundle
import com.android.hara.R
import com.android.hara.databinding.ActivityFinalResolveBinding
import com.android.hara.presentation.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinalResolveActivity :
    BindingActivity<ActivityFinalResolveBinding>(R.layout.activity_final_resolve) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("worryTitle")) {
            binding.title = intent.getStringExtra("worryTitle")
            binding.option = intent.getStringExtra("selOptTitle")
            binding.includeImg = intent.getBooleanExtra("includeImg", false)
        }

        binding.btnFinalGoStorage.setOnClickListener {
            // TODO: 보관함으로 이동 어떻게 하지?
        }
    }
}
