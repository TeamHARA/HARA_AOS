package com.android.hara.presentation.detail

import android.os.Bundle
import com.android.hara.R
import com.android.hara.databinding.ActivityDetailBinding
import com.android.hara.presentation.base.BindingActivity
import com.android.hara.presentation.custom.DecisionDialog
import com.android.hara.presentation.custom.model.DialogData

class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    //TODO [고민글 상세보기] 부분입니다. 추후 네미망 변경 예정
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvDetailCategory.setOnClickListener {
            DecisionDialog(
                this, DialogData(
                    getString(R.string.anonymity),
                    getString(R.string.anonymity),
                    getString(R.string.anonymity),
                    getString(R.string.anonymity)
                )
            ){}.showDialog()
        }
    }
}