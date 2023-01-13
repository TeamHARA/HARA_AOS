package com.android.hara.presentation.detail.decision

import android.content.Intent
import android.os.Bundle
import com.android.hara.R
import com.android.hara.databinding.ActivityFinalResolveBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.home.HomeActivity
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
            when (intent.getIntExtra("optionNum", 2)) {
                0 -> {
                    binding.ivFinalResolveImg.setImageResource(R.drawable.img_one_sec)
                }
                1 -> {
                    binding.ivFinalResolveImg.setImageResource(R.drawable.img_title_logo)
                }
                else -> {
                    binding.includeImg = false
                }
            }
        }

        binding.tvFinalGoStorage.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    HomeActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
                    .putExtra("isNextSolve", true)
            )
        }
    }
}
