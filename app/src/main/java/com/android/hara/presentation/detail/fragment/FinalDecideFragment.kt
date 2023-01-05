package com.android.hara.presentation.detail.fragment

import android.os.Bundle
import android.view.View
import com.android.hara.R
import com.android.hara.databinding.FragmentFinalDecideBinding
import com.android.hara.presentation.base.BindingFragment

class FinalDecideFragment :BindingFragment<FragmentFinalDecideBinding>(R.layout.fragment_final_decide) {
    // FinalDecideFragment에서 버튼을 다스릴 애가 local임.
    // local이 data binding으로 연결된 fragment_final_decide.xml에 있는 selected라는 변수로 전달됨.
    // .xml에서 selected가 버튼에 달려있어.
    // .xml에서 selected가 app.어쩌고에 연결돼있어.
    // util/BindingConversion.kt에 보면 app. 어쩌고라고 써있었음.
    // 그 .kt 파일에 보면 sel이 있어.
    // sel에 최종적으로 selected가 전달되는 것
    // [결론] Fragment의 local = .xml의 selected = BindingConversion.kt의 sel
    var local = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFinalSolve.setOnClickListener {
            local = !(local)
            binding.selected = local
        }
    }
}