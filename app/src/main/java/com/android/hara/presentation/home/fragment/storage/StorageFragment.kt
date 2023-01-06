package com.android.hara.presentation.home.fragment.storage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.onesec.OneSecActivity


class StorageFragment : BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 버튼 클릭 시 '1초만에 해결하기' 액티비티로 연결
        binding.cvBtnOnesec.setOnClickListener {
            val intent = Intent(activity, OneSecActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) // 이거 지워도 되나..
            startActivity(intent)
        }
    }
}