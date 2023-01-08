package com.android.hara.presentation.detail

import android.os.Bundle
import com.android.hara.R
import com.android.hara.databinding.ActivityFinalResolveBinding
import com.android.hara.presentation.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinalResolveActivity :BindingActivity<ActivityFinalResolveBinding>(R.layout.activity_final_resolve) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}