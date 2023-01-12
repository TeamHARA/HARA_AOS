package com.android.hara.presentation.search

import android.os.Bundle
import com.android.hara.R
import com.android.hara.databinding.ActivitySearchBinding
import com.android.hara.presentation.base.BindingActivity

class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.empty = false //검색어 없는 경우 = false
        binding.count = 4
        binding.appbarDetail.setNavigationOnClickListener {
            finish()
        }

    }
}