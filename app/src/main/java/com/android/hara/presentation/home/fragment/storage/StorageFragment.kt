package com.android.hara.presentation.home.fragment.storage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.hara.R
import com.android.hara.databinding.FragmentStorageBinding
import com.android.hara.presentation.base.BindingFragment
import com.android.hara.presentation.onesec.OneSecActivity


class StorageFragment : BindingFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    // FinalDecideFragment에서 버튼을 다스릴 애가 local임.
    // local이 data binding으로 연결된 fragment_final_decide.xml에 있는 selected라는 변수로 전달됨.
    // .xml에서 selected가 버튼에 달려있어.
    // .xml에서 selected가 app.어쩌고에 연결돼있어.
    // util/BindingConversion.kt에 보면 app. 어쩌고라고 써있었음.
    // 그 .kt 파일에 보면 sel이 있어.
    // sel에 최종적으로 selected가 전달되는 것
    // [결론] Fragment의 local = .xml의 selected = BindingConversion.kt의 sel

    val ing = true // 고민중이면 true, 고민완료면 false //TODO 서버통신 시 변경

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